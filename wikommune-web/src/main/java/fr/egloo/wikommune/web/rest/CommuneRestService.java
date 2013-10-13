package fr.egloo.wikommune.web.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;

import fr.egloo.wikommune.web.domain.Commune;
import fr.egloo.wikommune.web.service.CommuneService;

@Path("/communes")
public class CommuneRestService {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
    public Commune getDefaultCommuneInJSON() {
		CommuneService communeService = new CommuneService();
        return communeService.getDefaultCommune();
    }
	
	@GET
	@Path("/{param}")
    @Produces(MediaType.APPLICATION_JSON)
    public Commune getDefaultCommuneInJSON(@PathParam("param") String commune) {
		CommuneService communeService = new CommuneService();
		if(StringUtils.isEmpty(commune)){
			return communeService.getDefaultCommune();
		}
        return communeService.findCommune(commune);
    }
}
