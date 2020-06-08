package guru.springframework.petclinicdata.services.map;

import java.util.Set;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import guru.springframework.petclinicdata.model.Visit;
import guru.springframework.petclinicdata.services.VisitService;

/*
 *Created for jalemaov on 04-05-2020
 */
@Service
@Profile({"default","map"})
public class VisitMapService extends AbstractMapService<Visit, Long> implements VisitService {

	@Override
	public Set<Visit> findAll() {
		return null;
	}

	@Override
	public Visit findById(Long id) {
		return null;
	}

	@Override
	public Visit save(Visit object) {
		if(object.getPet() == null || 
				object.getPet().getOwner() == null ||
				object.getPet().getId() == null ||
				object.getPet().getOwner().getId() == null) {
			throw new RuntimeException("Invalid visit");
		}
		return super.save(object);
	}

	@Override
	public void delete(Visit object) {
		super.delete(object);
	}

	@Override
	public void deleById(Long id) {
		super.deleteById(id);
	}

}
