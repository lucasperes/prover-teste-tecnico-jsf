package br.com.provertec.model;

import java.io.Serializable;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CounterModel implements Serializable {
	
	private static final long serialVersionUID = -8823079941750047513L;
	
	private String palavra;
	private int occorencias;
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CounterModel other = (CounterModel) obj;
		return Objects.equals(palavra, other.palavra);
	}
	@Override
	public int hashCode() {
		return Objects.hash(palavra);
	}

}
