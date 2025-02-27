package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.betacom.backend.services.interfaces.messages.MessageServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ColorDTO;
import com.betacom.backend.model.products.Color;
import com.betacom.backend.repositories.products.IColorRepository;
import com.betacom.backend.request.products.ColorRequest;
import com.betacom.backend.services.interfaces.products.CaseServices;
import com.betacom.backend.services.interfaces.products.ColorServices;

@Service
public class ColorImpl implements ColorServices{

	@Autowired
	IColorRepository colRep;

	@Autowired
	MessageServices msgS;
	
	//per controllare che nessun case venga eliminato distruggendo la correlazione con 
	//il color abbiamo bisogno di controllare fattivamente che questo avvenga 
	//controllando quali abbiano il colore
	@Autowired
	CaseServices casS;

	@Override
	public List<ColorDTO> list() {
		 List<Color> lCol = colRep.findAll();

         return lCol.stream().map(c -> 
         		new ColorDTO(c)
        ).collect(Collectors.toList());
	}

	@Override
	public ColorDTO get(Long id) throws Exception {
		if(id == null){
            throw new Exception(msgS.getMessage("missing-id-get"));
        }

        Optional<Color> col = colRep.findById(id);

        if(col.isPresent()){
            return new ColorDTO(
            		col.get());
        }else{
            throw new Exception(msgS.getMessage("does-not-exist-get"));
        }
	}

	@Override
	public void create(ColorRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception(msgS.getMessage("missing-attributes-create"));

        Color c = new Color(req);
        colRep.save(c);
	}

	@Override
	public void update(ColorRequest req) throws Exception {
		 if(req.getId() == null){
	            throw new Exception(msgS.getMessage("missing-id-update"));
	        }

	        if( colRep.findById(req.getId()).isEmpty()){
	            throw new Exception(msgS.getMessage("missing-id-update"));
	        }

	        Color c = new Color(req);

	        colRep.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
			throw new Exception(msgS.getMessage("missing-id-delete"));
        }
	//da controllare eventuali problemi
		if(casS.list("EN").stream().filter
				(x->x.getColor().getId()==id).findFirst().isPresent())
			throw new Exception(msgS.getMessage("delete-is-case-present"));
			;
		
        colRep.deleteById(id);
	}
	
	private boolean mancanoAttributi(ColorRequest req) {
        return req.getColor() == null || req.getColor().isBlank();
    }
}
