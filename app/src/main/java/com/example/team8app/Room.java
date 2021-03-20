package com.example.team8app;

/* @Purpose: The Room class represents a room in the USB. Data about Rooms is stored in the database
 * @Created by  Matthew Pearson
 * @Since   06/04/2020
 */
public class Room {

    private String roomNo;
    private String description;
    private boolean smartCard;
    private int xPos;
    private int yPos;

    // Empty constructor required for Firestore serialisation
    public Room(){}

    /** Constructor for if manual creation of Room class is necessary
     *
     * @param roomNo
     * @param description
     * @param smartCard
     * @param xPos
     * @param yPos
     */
    public Room(String roomNo, String description, boolean smartCard, int xPos, int yPos) {
        this.roomNo = roomNo;
        this.description = description;
        this.smartCard = smartCard;
        this.xPos = xPos;
        this.yPos = yPos;
    }

    // Getters and setters (setters required for FireStore object serialisation)

    public String getRoomNo() {
        return roomNo;
    }

    public void setRoomNo(String roomNo) {
        this.roomNo = roomNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getSmartCard() {
        return smartCard;
    }

    public void setSmartCard(boolean smartCard) {
        this.smartCard = smartCard;
    }

    public int getxPos() {
        return xPos;
    }

    public void setxPos(int xPos) {
        this.xPos = xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setyPos(int yPos) {
        this.yPos = yPos;
    }
}
