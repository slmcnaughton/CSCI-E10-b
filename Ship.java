// Ship.java

/**
 *  This program is an abstract class for creating objects of type ship. Ships
 *  have a name, a year built, and an EngineType.
 *
 *  @author Seth McNaughton
 *  @version Created 3/3/2014
 */ 

enum  EngineType {STEAM_ENGINE, STEAM_TURBINE, GAS_TURBINE, DIESEL, 
						  ELECTRIC, WIND, HUMAN_FORCE}

abstract class Ship
{

	protected String shipName;
	protected int yearBuilt;
	protected EngineType engine;

	/*
	*   0-parameter constructor for the class, which sets the default
	*	name as null, year built in 1950, and the EngineType as wind.
	*/
	public Ship()
	{
		shipName = "";
		yearBuilt = 1950;
		engine = EngineType.WIND;
	}


	/* 3-paramater constructor for the class, which sets the name, year
	*  built, and the EngineType to the user's choice.
	*
    *  @param    name	    The name of the ship
    *  @param    yearBuilt    The year the ship was built
    *  @param    EngineType    The type of engine of the ship
    */
	public Ship(String name, int year, EngineType engine)
	{
		shipName = name;
		yearBuilt = year;
		this.engine = engine;
	}



	/* This getter method allows for the private variable shipName to be
	*  returned.
	*  
	*  @return		private variable shipName
	*/
	public String getShipName()
	{
		return shipName;
	}

	/* This getter method allows for the private variable yearBuilt to be
	*  returned.
	*  
	*  @return		private variable yearBuilt
	*/
	public int getYearBuilt()
	{
		return yearBuilt;
	}


	/* This getter method allows for the private variable engineType to be
	*  returned.
	*  
	*  @return		private variable engineType
	*/
	public EngineType getEngineType()
	{
		return engine;
	}

	/* Setter method for shipName
    *  @param    shipName    The name of the ship
	*/
	public void setShipName(String shipName)
	{
		this.shipName = shipName;
	}

	/* Setter method for yearBuilt
    *  @param    yearBuilt    The year the ship was built
	*/
	public void setYearBuilt(int yearBuilt)
	{
		this.yearBuilt = yearBuilt;
	}

	/* Setter method for EngineType
    *  @param    EngineType    The type of engine of the ship
	*/
	public void setEngineType(EngineType engine)
	{
		this.engine = engine;
	}


	/*
    *  This method will print out basic information about the object
    *
    *  @return     String of basic information about the object
    */
	public String toString()
	{
		return "The magnificient ship " + shipName + " was built in " + 
				yearBuilt + " is propelled by " + engine + ".";
	}

}



class Cruiseship extends Ship
{
	protected int maxPassengers;
	protected boolean norovirus;

	/*
	*   0-parameter constructor for the class, which sets the default
	*	maxPassengers to 3000, and the norovirus not active on the ship.
	*/
	public Cruiseship()
	{
		shipName = "";
		maxPassengers = 3000;
		norovirus = false;
	}


	/* 3-paramater constructor for the class, which sets the name, year
	*  built, and the EngineType to the user's choice.
	*
    *  @param    name    The name of the ship
    *  @param    maxP    The maximum number of passengers on the ship
    *  @param    norovirus    True if dreaded norovirus is currently infecting
    *					      the ship.
    */
	public Cruiseship(String name, int maxP, boolean virus)
	{
		shipName = name;
		maxPassengers = maxP;
		norovirus = virus;
	}


	/* This getter method allows for the private variable maxPassengers to be
	*  returned.
	*  
	*  @return		private variable maxPassengers
	*/
	public int getMaxPassengers()
	{
		return maxPassengers;
	}



	/* This getter method allows for the private variable norovirus to be
	*  returned.
	*  
	*  @return		private variable norovirus
	*/
	public boolean getNorovirus()
	{
		return norovirus;
	}

	
	/* Setter method for maxPassengers
    *  @param    maxPassengers    The maximum number of passengers the ship holds
	*/
	public void setMaxPassengers(int maxPassengers)
	{
		this.maxPassengers = maxPassengers;
	}


	/* Setter method for norovirus
    *  @param    norovirus    True if dreaded norovirus is currently infecting 
    *						  passengers. False if ship is safe.
	*/
	public void setNorovirus(boolean norovirus)
	{
		this.norovirus = norovirus;
	}	
	

	/*
    *  This method will print out basic information about the object
    *
    *  @return     String of basic information (name and passenger capacity) 
    *              of the ship.
    */
	public String toString()
	{
		return "The cruise ship " + shipName + " has a maximum capacity of " + 
				maxPassengers + " passengers.";
	}
}




class CargoShip extends Ship
{
	protected double cargoCapacity;

	/*
	*   0-parameter constructor for the class, which sets the default 
	*   cargoCapacity to 20000.
	*/
	public CargoShip()
	{
		shipName = "";
		cargoCapacity = 20000;
	}


	/* 2-paramater constructor for the class, which sets the name, year
	*  built, and the EngineType to the user's choice.
	*
    *  @param    name    The name of the ship
    *  @param    cargoCapacity    The tonnage the ship can carry
    */
	public CargoShip(String name, double capacity)
	{
		shipName = name;
		cargoCapacity = capacity;
	}



	/* This getter method allows for the private variable cargoCapacity to be
	*  returned.
	*  
	*  @return	 	private variable cargoCapacity
	*/
	public double getCargoCapacity()
	{
		return cargoCapacity;
	}

	
	/* Setter method for cargoCapacity
    *  @param    cargoCapcity    The maximum weight, in tons, the ship holds
	*/
	public void setCargoCapacity(double cargoCapacity)
	{
		this.cargoCapacity = cargoCapacity;
	}
	

	/*
    *  This method will print out basic information about the object
    *
    *  @return     String of basic information (name and capacity) of the ship
    */
	public String toString()
	{
		return "CargoShip " + shipName + " has a cargo capacity of " 
				+ cargoCapacity + " tons";
	}

}


enum  TankerCargo {OIL, CHEMICALS, GAS}

class Tanker extends CargoShip
{
	protected TankerCargo tank;

	/*
	*   0-parameter constructor for the class, which sets the default 
	*   cargoCapacity to 100000 and the type to OIL.
	*/
	public Tanker()
	{
		shipName = "";
		cargoCapacity = 100000;
		tank = TankerCargo.OIL;
	}

	/* 3-paramater constructor for the class, which sets the name, year
	*  built, and the EngineType to the user's choice.
	*
    *  @param    name    The name of the ship
    *  @param    cargoCapacity    The tonnage the ship can carry
    *  @param	 tank		The type of cargo the tanker is carrying
    */
	public Tanker (String name, double capacity, TankerCargo tank)
	{
		shipName = name;
		cargoCapacity = capacity;
		this.tank = tank;
	}
	


	/* This getter method allows for the private variable tank to be returned.
	*  
	*  @return		private variable tank
	*/
	public TankerCargo getTank()
	{
		return tank;
	}

	
	/* Setter method for maxPassengers
    *  @param    maxPassengers    The maximum number of passengers the ship holds
	*/
	public void setTankerCargo (TankerCargo tank)
	{
		this.tank = tank;
	}

	/*
    *  This method will print out basic information about the object
    *
    *  @return     String of basic information (name,capacity, and tankerCargo)
    *  				of the ship
    */
	public String toString()
	{
		return super.toString() + " and is carrying " + tank;
	}
}



