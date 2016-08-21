/**
 * Wrapper class for a Cluedo weapon
 */
package cluedoModel;

public class Weapon implements Card{

	private String name;

	public Weapon(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return name;
	}

}
