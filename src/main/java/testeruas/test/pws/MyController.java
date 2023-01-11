/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testeruas.test.pws;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author asus
 */
@RestController
@CrossOrigin
public class MyController {
    MahasiswaJpaController control = new MahasiswaJpaController();
	
	@PostMapping("/POST")
	public String sendData(HttpEntity<String> kiriman) throws Exception{
		Mahasiswa datas = new Mahasiswa();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Mahasiswa.class);
	        control.create(datas);
		return d;
	}
	
	@PutMapping("/PUT")
	public String editData(HttpEntity<String> kiriman) throws Exception{
		Mahasiswa datas = new Mahasiswa();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Mahasiswa.class);
	        control.edit(datas);
		return d;
	}
	
	@DeleteMapping("/DELETE")
	public String deleteData(HttpEntity<String> kiriman) throws Exception{
		Mahasiswa datas = new Mahasiswa();
		String d = kiriman.getBody();
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		datas = mapper.readValue(d, Mahasiswa.class);
	        control.destroy(datas.getNim());
		return "nim: "+datas.getNim()+" deleted";
	}
	
	@GetMapping("/GET")
	public List<Mahasiswa> getTabel(){
		List<Mahasiswa> list = new ArrayList<>();
		try {
			list = control.findMahasiswaEntities();
		}
		catch (Exception e){}
		return list;
	}
}
