package aboidsim.model;

/**
 *
 * enum with life of single component of the environment. Enum arguments:
 *
 * - boid Level - boid Life - boid Influence Radius - Max boids of same group
 *
 */

enum Entities {

	TREE_L0("TREE", "tree.png", 0, 500, 30, 0, 0), 
	HERBIVORE_L1("HERBIVORE - LEVEL 1", "herbivore0.png", 1, 100, 50, 10, 5), 
	HERBIVORE_L2("HERBIVORE - LEVEL 2", "herbivore1.png", 2, 200, 55, 9, 6), 
	HERBIVORE_L3("HERBIVORE - LEVEL 3", "herbivore2.png", 3, 300, 60, 7, 7), 
	HERBIVORE_L4("HERBIVORE - LEVEL 4", "herbivore3.png", 4, 400, 65, 7, 8), 
	HERBIVORE_L5("HERBIVORE - LEVEL 5", "herbivore4.png", 5, 500, 70, 6, 9), 
	PREDATOR_L6("PREDATOR - LEVEL 6", "predator0.png", 6, 600, 75, 5, 11), 
	PREDATOR_L7("PREDATOR - LEVEL 7", "predator1.png", 7, 700, 80, 4, 12), 
	PREDATOR_L8("PREDATOR - LEVEL 8", "predator2.png", 8, 800, 85, 3, 13), 
	PREDATOR_L9("PREDATOR - LEVEL 9", "predator3.png", 9, 900, 90, 2, 14), 
	PREDATOR_L10("PREDATOR - LEVEL 10", "predator4.png", 10, 1000, 95, 1, 15);

	private final String name;
	private final String image;
	private final int id;
	private final int life;
	private final double influenceRadius;
	private final int maxMembers;
	private final double maxSpeed;

	/**
	 *
	 * @param id
	 *            boid Level
	 * @param lif
	 *            boid Life
	 * @param rad
	 *            boid Influence Radius
	 * @param max_m
	 *            Max boids of same group
	 * @param maxSpeed
	 *            boid max speed
	 */
	Entities(final String nam, final String img, final int iD, final int lif, final double rad, final int maxM,
			final double mSpeed) {
		this.name = nam;
		this.image = img;
		this.id = iD;
		this.life = lif;
		this.influenceRadius = rad;
		this.maxMembers = maxM;
		this.maxSpeed = mSpeed;
	}

	public String getName() {
		return this.name;
	}

	public String getImage() {
		return this.image;
	}

	public int getId() {
		return this.id;
	}

	public int getLife() {
		return this.life;
	}

	public double getInfluenceRadius() {
		return this.influenceRadius;
	}

	public int getMaxMembers() {
		return this.maxMembers;
	}

	public double getMaxSpeed() {
		return this.maxSpeed;
	}
}
