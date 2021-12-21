package com.api.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.geo.Point;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.api.dto.MapPointsDto;
import com.api.dto.Prediction;
import com.api.dto.SearchGoongMap;
import com.api.dto.SearchMapResponse;
import com.api.entity.Organization;
import com.api.entity.ReliefPoint;
import com.api.entity.SOS;
import com.api.entity.Store;
import com.api.mapper.MapStructMapper;
import com.api.repositories.MapRepository;
import com.api.repositories.OrganizationRepository;
import com.api.repositories.ReliefPointRepository;
import com.api.repositories.SOSRepository;
import com.api.repositories.StoreRepository;
import com.api.service.MapService;
import com.api.service.SOSService;
import com.ultils.Constants;

@Service
public class MapSerivceImpl implements MapService {

	@Autowired
	ReliefPointRepository reliefPointRepository;

	@Autowired
	OrganizationRepository organizationRepository;

	@Autowired
	StoreRepository storeRepository;

	@Autowired
	SOSRepository SOSRepository;

	@Override
	public double distanceBetween2Points(double la1, double lo1, double la2, double lo2) {
		// TODO Auto-generated method stub
		double R = 6371;
		double dLat = (la2 - la1) * (Math.PI / 180);
		double dLon = (lo2 - lo1) * (Math.PI / 180);
		double la1ToRad = la1 * (Math.PI / 180);
		double la2ToRad = la2 * (Math.PI / 180);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(la1ToRad) * Math.cos(la2ToRad) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double d = R * c;
		return d;
	}

	@Override
	public List<?> findPointinRadius(double la, double lo, double radius, Class<?> destinationType) {
		// TODO Auto-generated method stub
		List<?> lstRs = null;
		if (destinationType.equals(ReliefPoint.class)) {
			List<ReliefPoint> lstRp = reliefPointRepository.findAll();
			lstRs = lstRp.stream().filter((rp) -> {
				double distance = distanceBetween2Points(Double.parseDouble(rp.getAddress().getGPS_Lati()),
						Double.parseDouble(rp.getAddress().getGPS_Long()), la, lo);
				if (distance > radius)
					return false;
				return true;
			}).collect(Collectors.toList());

		}

		return lstRs;
	}

	public List<MapPointsDto> findReliefPoint(double la, double lo, double radius) {
		List<ReliefPoint> rps = reliefPointRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (ReliefPoint rp : rps) {
			MapPointsDto mp = new MapPointsDto();
			if (rp.getAddress().getGPS_Lati() == null || rp.getAddress().getGPS_Lati().isBlank()
					|| rp.getAddress().getGPS_Long() == null || rp.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			mp.setId(rp.getId());
			mp.setType(Constants.MAP_TYPE_RELIEFPOINT);
			mp.setPoint(new Point(Double.parseDouble(rp.getAddress().getGPS_Lati()),
					Double.parseDouble(rp.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}
		return mapPoint;
	}

	public List<MapPointsDto> findStores(double la, double lo, double radius) {
		List<Store> stores = storeRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (Store store : stores) {
			if (store.getLocation().getGPS_Lati() == null || store.getLocation().getGPS_Lati().isBlank()
					|| store.getLocation().getGPS_Long() == null || store.getLocation().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(store.getId());
			mp.setType(Constants.MAP_TYPE_STORE);
			mp.setPoint(new Point(Double.parseDouble(store.getLocation().getGPS_Lati()),
					Double.parseDouble(store.getLocation().getGPS_Long())));
			mp.setName(store.getName());
			mapPoint.add(mp);
		}
		return mapPoint;
	}

	public List<MapPointsDto> findOrganization(double la, double lo, double radius) {
		List<Organization> orgs = organizationRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (Organization org : orgs) {
			if (org.getAddress().getGPS_Lati() == null || org.getAddress().getGPS_Lati().isBlank()
					|| org.getAddress().getGPS_Long() == null || org.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(org.getId());
			mp.setType(Constants.MAP_TYPE_ORGANIZATION);
			mp.setPoint(new Point(Double.parseDouble(org.getAddress().getGPS_Lati()),
					Double.parseDouble(org.getAddress().getGPS_Long())));
			mp.setName(org.getName());
			mapPoint.add(mp);
		}

		return mapPoint;
	}

	public List<MapPointsDto> findSOS(double la, double lo, double radius) {
		List<SOS> SOSs = SOSRepository.findAll();
		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();
		for (SOS sos : SOSs) {
			if (sos.getAddress().getGPS_Lati() == null || sos.getAddress().getGPS_Lati().isBlank()
					|| sos.getAddress().getGPS_Long() == null || sos.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(sos.getId());
			mp.setType(Constants.MAP_TYPE_SOS);
			mp.setPoint(new Point(Double.parseDouble(sos.getAddress().getGPS_Lati()),
					Double.parseDouble(sos.getAddress().getGPS_Long())));
			mapPoint.add(mp);
		}

		return mapPoint;
	}

	@Override
	public List<MapPointsDto> findAllPoints(double la, double lo, double radius) {
		// TODO Auto-generated method stub
		List<ReliefPoint> rps = reliefPointRepository.findAll();
		List<Store> stores = storeRepository.findAll();
		List<Organization> orgs = organizationRepository.findAll();

		List<MapPointsDto> mapPoint = new ArrayList<MapPointsDto>();

		for (ReliefPoint rp : rps) {
			MapPointsDto mp = new MapPointsDto();
			if (rp.getAddress().getGPS_Lati() == null || rp.getAddress().getGPS_Lati().isBlank()
					|| rp.getAddress().getGPS_Long() == null || rp.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			mp.setId(rp.getId());
			mp.setType(Constants.MAP_TYPE_RELIEFPOINT);
			mp.setPoint(new Point(Double.parseDouble(rp.getAddress().getGPS_Lati()),
					Double.parseDouble(rp.getAddress().getGPS_Long())));
			mp.setName(rp.getName());
			mapPoint.add(mp);
		}

		for (Store store : stores) {
			if (store.getLocation().getGPS_Lati() == null || store.getLocation().getGPS_Lati().isBlank()
					|| store.getLocation().getGPS_Long() == null || store.getLocation().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(store.getId());
			mp.setType(Constants.MAP_TYPE_STORE);
			mp.setPoint(new Point(Double.parseDouble(store.getLocation().getGPS_Lati()),
					Double.parseDouble(store.getLocation().getGPS_Long())));
			mp.setName(store.getName());
			mapPoint.add(mp);
		}

		for (Organization org : orgs) {
			if (org.getAddress().getGPS_Lati() == null || org.getAddress().getGPS_Lati().isBlank()
					|| org.getAddress().getGPS_Long() == null || org.getAddress().getGPS_Long().isBlank()) {
				continue;
			}
			MapPointsDto mp = new MapPointsDto();
			mp.setId(org.getId());
			mp.setType(Constants.MAP_TYPE_ORGANIZATION);
			mp.setPoint(new Point(Double.parseDouble(org.getAddress().getGPS_Lati()),
					Double.parseDouble(org.getAddress().getGPS_Long())));
			mp.setName(org.getName());
			mapPoint.add(mp);
		}

		mapPoint = mapPoint.stream().filter((mp) -> {
			double distance = distanceBetween2Points(mp.getPoint().getX(), mp.getPoint().getY(), la, lo);
			if (distance > radius)
				return false;
			return true;
		}).collect(Collectors.toList());

		return mapPoint;
	}

	public void search(String searchStr) {
		// TODO Auto-generated method stub

	}

	@Value("${goong.map.api.key}")
	private String api_key;

	@Autowired
	MapRepository mapRepository;

	@Override
	public List<SearchMapResponse> search(String text, double lati, double longti, int numberOfRecord) {
		List<SearchMapResponse> lstSearchMapRes = new ArrayList<SearchMapResponse>();

		List<Object[]> lstRs = mapRepository.search(text, lati, longti, numberOfRecord);
		for (Object[] obj : lstRs) {
			SearchMapResponse smr = new SearchMapResponse();
			BigInteger id = (BigInteger) obj[0];
			smr.setPlace_id(id.toString());
			smr.setName((String) obj[1]);
			HashMap<String, String> location = new HashMap<String, String>();
			location.put("latitu", (String) obj[2]);
			location.put("longtitude", (String) obj[3]);
			smr.setLocation(location);
			smr.setDescription((String) obj[4]);
			smr.setType((String) obj[5]);
			lstSearchMapRes.add(smr);
		}
		// check if number of record is lack to call api goong map
		if (lstRs.size() < numberOfRecord) {
			int record = numberOfRecord - lstRs.size();
			SearchGoongMap searchGoongMap = searchApiGoongMap(text, lati, longti, record);
			List<Prediction> lstPredictions = searchGoongMap.getPredictions();
			lstPredictions.forEach((pre) -> {
				SearchMapResponse smr = new SearchMapResponse();
				smr.setPlace_id(pre.getPlace_id());
				smr.setDescription(pre.getDescription());
				lstSearchMapRes.add(smr);
			});
		}

		return lstSearchMapRes;

	}

	public SearchGoongMap searchApiGoongMap(String searchText, double lati, double longti, int limit) {

		String url = "https://rsapi.goong.io/Place/AutoComplete?api_key=" + api_key + "&location=" + lati + "," + longti
				+ "&input=" + searchText + "&limit=" + limit;

		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Yêu cầu trả về định dạng JSON
		headers.setContentType(MediaType.APPLICATION_JSON);

		// HttpEntity<String>: To get result as String.
		HttpEntity<SearchGoongMap> entity = new HttpEntity<SearchGoongMap>(headers);

		// RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// Gửi yêu cầu với phương thức GET, và các thông tin Headers.
		ResponseEntity<SearchGoongMap> response = restTemplate.exchange(url, HttpMethod.GET, entity,
				SearchGoongMap.class);

		HttpStatus statusCode = response.getStatusCode();

		SearchGoongMap searchGoongMap = null;
		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			searchGoongMap = response.getBody();
		}
		return searchGoongMap;

	}

	@Override
	public List<MapPointsDto> findAllPoints(double la, double lo, double radius, String typePoint) {
		// TODO Auto-generated method stub
		if (typePoint == null || typePoint.isBlank()) {
			typePoint = Constants.MAP_TYPE_RELIEFPOINT + "," + Constants.MAP_TYPE_SOS + "," + Constants.MAP_TYPE_STORE
					+ "," + Constants.MAP_TYPE_ORGANIZATION;
		}

		List<MapPointsDto> lstMapPoints = new ArrayList<MapPointsDto>();
		List<Object[]> mapPoints = mapRepository.getPoints(la, lo, radius, typePoint);
		for (Object[] obj : mapPoints) {
			MapPointsDto mp = new MapPointsDto();
			BigInteger id = (BigInteger) obj[0];
			BigInteger user_id = (BigInteger) obj[4];
			mp.setId(id.longValue());
			mp.setName((String) obj[1]);
			mp.setPoint(new Point(Double.valueOf(obj[2].toString()), Double.valueOf(obj[3].toString())));
			mp.setType((String) obj[5]);
			mp.setUser_id(user_id.longValue());
			lstMapPoints.add(mp);
		}

		return lstMapPoints;
	}

}
