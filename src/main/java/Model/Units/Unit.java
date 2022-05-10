package Model.Units;

import Model.Map;
import Model.Player;
import Model.Tile;
import enums.OrderType;
import enums.UnitType;

public class Unit {
    private Tile tile;
    private Player owner;
    private final int health; // this is for refrence
    private int cost;
    private int movement;
    private int MP; // needs to be refilled at the end of each turn
    private int HP; // this is what changes in attacks
    private int sightRange;
    private UnitType unitType;
    private Tile destination;
    private int remainingCost;
    private OrderType orderType;
    // TODO: 4/17/2022 : Unit order handling (orders should be passed on between turns)

    public Unit(Tile tile, Player owner, UnitType unitType) {
        this.tile = tile;
        this.owner = owner;
        this.unitType = unitType;
        this.destination = this.tile;
        this.health = 10;
        this.cost = unitType.cost;
        this.movement = unitType.movement;
        this.sightRange = 2;
        this.HP = this.health;
        this.MP = this.movement;
        this.remainingCost = this.cost;
        this.orderType = OrderType.AWAKE;
        if(this.tile != null) this.tile.putUnit(this);
    }

    public Tile getTile() {
        return tile;
    }

    public void setTile(Tile tile) {
        this.tile = tile;
    }

    public Player getOwner() {
        return owner;
    }

    public void setOwner(Player owner) {
        this.owner = owner;
    }

    public int getHealth() {
        return health;
    }


    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getMovement() {
        return movement;
    }

    public void setMovement(int movement) {
        this.movement = movement;
    }

    public int getMP() {
        return MP;
    }

    public void setMP(int MP) {
        this.MP = MP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getSightRange() {
        return sightRange;
    }

    public void setSightRange(int sightRange) {
        this.sightRange = sightRange;
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    public Tile getDestination() {
        return destination;
    }

    public void setDestination(Tile destination) {
        this.destination = destination;
    }

    public int getRemainingCost() {
        return remainingCost;
    }

    public void setRemainingCost(int remainingTurn) {
        this.remainingCost = remainingTurn;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public int getRow(){
        return tile.getRow();
    }

    public int getColumn(){
        return tile.getColumn();
    }

    public void placeIn(Tile tile, Map map){
        this.MP -= tile.getMP(this.tile);
        if(tile.getCity() != null && !tile.getCity().getOwner().equals(this.owner)){
            this.MP = 0;
        }
        for (Tile neighbouringTile : tile.getNeighbouringTiles(map)) {
            if(neighbouringTile.getTroop() != null && neighbouringTile.getTroop().getOwner() != this.owner){
                this.MP = 0;
            }
        }
        this.tile.takeUnit(this);
        tile.putUnit(this);
        this.tile = tile;
    }

    public void destroy(){
        this.tile.takeUnit(this);
        this.owner.removeUnit(this);
    }
}

