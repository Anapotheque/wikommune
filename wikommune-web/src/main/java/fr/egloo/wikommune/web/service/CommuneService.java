package fr.egloo.wikommune.web.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import fr.egloo.wikommune.web.domain.Commune;

public class CommuneService {
	
	private List<Commune> list;
	
	// TODO METRE UN BOUCHON
	public CommuneService(){
		list = new ArrayList<Commune>();
		list.add(new Commune("Toulon","83000"));
		list.add(new Commune("Nanterre","92000"));
		list.add(new Commune("Paris","75000"));
	}
	
	public Commune getDefaultCommune() {
		Commune commune = null;
		if(CollectionUtils.isNotEmpty(list)){
			commune = list.get(0);
		}
        return commune;
    }
	
	public Commune findCommune(String commune) {
		if(CollectionUtils.isNotEmpty(list)){
			for(Commune current : list){
				if(current.getLibelle().equals(commune)){
					return current;
				}
			}
		}
        return null;
    }
}
