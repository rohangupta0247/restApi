package com.saptris.restApi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saptris.restApi.dto.AssetDTO;
import com.saptris.restApi.repository.AssetRepository;

@Service
public class AssetService {
	@Autowired
	AssetRepository assetRepo;
	public List<AssetDTO> getAllAssets(){
		return assetRepo.getAllAssets();
	}
	public AssetDTO getAsset(String assetId) {
		return assetRepo.getAsset(assetId);
	}
	public String createAsset(AssetDTO asset) {
		return assetRepo.createAsset(asset);
	}
}
