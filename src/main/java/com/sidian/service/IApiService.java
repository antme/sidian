package com.sidian.service;

import java.util.List;

import com.sidian.bean.BaseEntity;
import com.sidian.bean.TDefSku;
import com.sidian.bean.TDefStore;
import com.sidian.bean.TFitting;
import com.sidian.bean.TSysUser;

public interface IApiService {

	public TSysUser login(TSysUser user);

	public List<TDefStore> listStore();

	public TDefSku checkSku(TDefSku sku);

	public List<TDefSku> listSku();

	public int addFittings(List<TFitting> fittings);

	public int deleteFittings(List<TFitting> fittings);

}
