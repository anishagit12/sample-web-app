package dto;

import com.fullcrudops.crudform.Entity.CrudAppEntity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CrudAppdto {
	private Long id;
	private String name;
	private String email;
	private String stateName;	
	
	public CrudAppdto(CrudAppEntity user) {
		
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.stateName = (user.getState() != null) ? user.getState().getName(): null;
	}
}