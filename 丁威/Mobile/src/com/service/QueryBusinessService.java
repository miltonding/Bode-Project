package com.service;

import java.util.List;

import com.dao.QueryBusineeDao;
import com.dao.iface.QueryBusinessDaoIface;
import com.domian.Business;
import com.domian.Package;
import com.service.iface.QueryBusinessServiceIface;

public class QueryBusinessService implements QueryBusinessServiceIface{

	private QueryBusinessDaoIface queryBusinessDao=null;
	
	
	public QueryBusinessService() {
		super();
		queryBusinessDao=new QueryBusineeDao();
	}


	public List<Business> queryFreeBusiness(String tel_numb) {
		String sql="SELECT business_name,business_charge,start_time,end_time,is_optional,is_largess  FROM t_freebusiness f WHERE tel_numb="+tel_numb;
		return queryBusinessDao.queryFreeBusiness(sql, tel_numb);
	}
	


	public List<Business> queryNotOpenChargeBusiness(String tel_numb) {
		String sql="SELECT f.business_name,f.business_charge,f.effective_time,f.end_time,f.is_optional,f.is_largess FROM t_business_fee f WHERE business_charge!=0 AND f.business_id  NOT IN ( select t.business_id from t_mobile_package t WHERE tel_numb="+tel_numb+"AND t.status=1 AND t.business_id!=0)";
		return queryBusinessDao.queryNotOpenChargeBusiness(sql, tel_numb);
	}


	public List<Business> queryOpenChargeBusiness(String tel_numb) {
		//开通的收费业务
		String sql="";
		sql+=" SELECT m.business_id,f.business_name,f.business_charge,m.start_time,m.end_time,f.is_optional,f.is_largess,m.status";
		sql+=" FROM (SELECT *";
				sql+=" FROM t_mobile_package t";
				sql+=" WHERE tel_numb="+tel_numb;
				sql+=" AND t.business_id!=0) m";
		sql+=" JOIN t_business_fee f";
		sql+=" ON m.business_id=f.business_id";
		return queryBusinessDao.queryOpenChargeBusiness(sql, tel_numb);

	}


	public List<Package> queryOpenPackage(String tel_numb) {
		String sql="";
		sql+=" SELECT n.pp_id,p2.pp_name,p2.pp_fee,f.business_name";
			sql+=" FROM  (SELECT pp_id,p.business_id";
			sql+=" FROM t_package_business p";
				sql+=" WHERE p.pp_id=(SELECT pp_id";
				sql+=" FROM t_mobile_package t";
			sql+="   WHERE t.tel_numb=?";
				sql+=" AND pp_id!=0";
			sql+="  AND status=1)";
			sql+="  AND p.business_id NOT IN(   SELECT business_id";
				sql+=" FROM t_business_fee ";
					sql+=" WHERE is_largess=0)) n";
					sql+=" JOIN t_phone_package p2";
						sql+=" ON n.pp_id=p2.pp_id";
							sql+=" JOIN t_business_fee f";
							sql+=" ON n.business_id=f.business_id";
						                            	
		return queryBusinessDao.queryOpenPackage(sql, tel_numb);
	}



	public List<Package> queryNotOpenPackage(String tel_numb) {
		String sql="";
		sql+=" SELECT n.pp_id,p2.pp_name,p2.pp_fee,f.business_name";
			sql+=" FROM  (SELECT pp_id,p.business_id";
			sql+=" FROM t_package_business p";
				sql+=" WHERE p.pp_id!=(SELECT pp_id";
				sql+=" FROM t_mobile_package t";
			sql+="   WHERE t.tel_numb=?";
				sql+=" AND pp_id!=0";
			sql+="  AND status=1)";
			sql+="  AND p.business_id NOT IN(   SELECT business_id";
				sql+=" FROM t_business_fee ";
					sql+=" WHERE is_largess=0)) n";
					sql+=" JOIN t_phone_package p2";
						sql+=" ON n.pp_id=p2.pp_id";
							sql+=" JOIN t_business_fee f";
							sql+=" ON n.business_id=f.business_id";
				sql+=" ORDER BY n.pp_id	";		                            	
		return queryBusinessDao.queryNotOpenPackage(sql, tel_numb);
	}
	public static void main(String[] args) {
		QueryBusinessService service=new QueryBusinessService();
		service.queryNotOpenPackage("13862203000");
	}

}
