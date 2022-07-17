package com.saptris.restApi.repository;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.saptris.restApi.dto.AssetDTO;

@Repository
public class AssetRepository {
	List<AssetDTO> assetsList;
	@PostConstruct
	public void intialize() {
		System.out.println("init asset repo");
		assetsList=new ArrayList<>();
	}
	public List<AssetDTO> getAllAssets(){
		return assetsList;
	}
	public AssetDTO getAsset(String assetId){
		AssetDTO asset=assetsList.stream().filter(x->x.getAssetId().equals(assetId)).findFirst().orElse(null);
		return asset;
	}
	public String createAsset(AssetDTO asset) {
		Long epoch=LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
		String assetId=epoch.toString().substring(6);
		asset.setAssetId(assetId);
		assetsList.add(asset);
		return asset.getAssetId();
	}
}
