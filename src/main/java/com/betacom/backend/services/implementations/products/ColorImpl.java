package com.betacom.backend.services.implementations.products;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.betacom.backend.dto.products.ColorDTO;
import com.betacom.backend.model.products.Color;
import com.betacom.backend.repositories.products.IColorRepository;
import com.betacom.backend.request.products.ColorRequest;
import com.betacom.backend.services.interfaces.products.ColorServices;

@Service
public class ColorImpl implements ColorServices{

	@Autowired
	IColorRepository colRep;

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
            throw new Exception("missing-id");
        }

        Optional<Color> col = colRep.findById(id);

        if(col.isPresent()){
            return new ColorDTO(
            		col.get());
        }else{
            throw new Exception("not-found");
        }
	}

	@Override
	public void create(ColorRequest req) throws Exception {
		if(mancanoAttributi(req))
            throw new Exception("missing-attributes");

        Color c = new Color(req);
        colRep.save(c);
	}

	@Override
	public void update(ColorRequest req) throws Exception {
		 if(req.getId() == null){
	            throw new Exception("missing-id");
	        }

	        if( colRep.findById(req.getId()).isEmpty()){
	            throw new Exception("does-not-exists");
	        }

	        Color c = new Color(req);

	        colRep.save(c);
	}

	@Override
	public void delete(Long id) throws Exception {
		if(id == null){
            throw new Exception("missing-id");
        }

        colRep.deleteById(id);
	}
	
	private boolean mancanoAttributi(ColorRequest req) {
        return req.getColor() == null || req.getColor().isBlank();
    }
}
