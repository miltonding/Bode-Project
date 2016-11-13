package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dao.AllPackageDao;
import com.dao.iface.AllPackageDaoIface;
import com.domian.Package;
import com.service.iface.AllPackageServiceIface;

public class AllPackageService implements AllPackageServiceIface {

	private AllPackageDaoIface allPackageDao=null;
	
	
	public AllPackageService() {
		super();
		allPackageDao=new AllPackageDao();
	}


	public Map<String, List> selectAllBusiness() {
		Map<String, List> freeMap=new HashMap<String, List>();
		List<String> freeListOne=new ArrayList<String>();
		List<String> freeListTwo=new ArrayList<String>();
		String sql="SELECT * FROM T_ALLBUSINESS";
		List<Package> list= allPackageDao.selectAllBusiness(sql);

		for (int i = 0; i < list.size(); i++) {
			Package b=list.get(i);
			//ÆæÊý
			if(i%2==0){
				String freeBusiness=b.getFreeBussiness();
				freeListOne.add(freeBusiness);
			}else{
				String freeBusiness=b.getFreeBussiness();
				freeListTwo.add(freeBusiness);
				
			}
		}

		freeMap.put("list", list);
		freeMap.put("freeListOne",freeListOne);
		freeMap.put("freeListTwo",freeListTwo);
		
		return freeMap;
		
		
	}

}
