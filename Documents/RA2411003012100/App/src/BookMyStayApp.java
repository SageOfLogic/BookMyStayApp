import java.util.*;

// Domain Model: Room
class Room {
    private String roomType;
    private double price;
    private List<String> amenities;

    public Room(String roomType, double price, List<String> amenities) {
        this.roomType = roomType;
        this.price = price;
        this.amenities = amenities;
    }

    public String getRoomType() {
        return roomType;
    }

    public double getPrice() {
        return price;
    }

    public List<String> getAmenities() {
        return Collections.unmodifiableList(amenities); // Defensive: read-only
    }

    @Override
    public String toString() {
        return "Room Type: " + roomType +
                ", Price: ₹" + price +
                ", Amenities: " + amenities;
    }
}

// Inventory: State Holder (Read-only during search)
class Inventory {
    private Map<String, Integer> availabilityMap;

    public Inventory() {
        availabilityMap = new HashMap<>();
    }

    // Used elsewhere (not in search) to modify inventory
    public void setAvailability(String roomType, int count) {
        availabilityMap.put(roomType, count);
    }

    // Read-only access
    public int getAvailability(String roomType) {
        return availabilityMap.getOrDefault(roomType, 0);
    }

    public Map<String, Integer> getAllAvailability() {
        return Collections.unmodifiableMap(availabilityMap); // Defensive copy
    }
}

// Search Service: Handles read-only search logic
class SearchService {
    private Inventory inventory;
    private Map<String, Room> roomCatalog;

    public SearchService(Inventory inventory, Map<String, Room> roomCatalog) {
        this.inventory = inventory;
        this.roomCatalog = roomCatalog;
    }

    // Core Search Logic (Read-only)
    public List<Room> searchAvailableRooms() {
        List<Room> availableRooms = new ArrayList<>();

        for (Map.Entry<String, Integer> entry : inventory.getAllAvailability().entrySet()) {
            String roomType = entry.getKey();
            int availableCount = entry.getValue();

            // Validation: skip invalid or unavailable rooms
            if (availableCount <= 0) {
                continue;
            }

            Room room = roomCatalog.get(roomType);

            // Defensive check: ensure room exists
            if (room != null) {
                availableRooms.add(room);
            }
        }

        return availableRooms;
    }
}

// Main Application
public class BookMyStayApp {

    public static void main(String[] args) {

        // Setup Room Catalog (Domain Model)
        Map<String, Room> roomCatalog = new HashMap<>();
        roomCatalog.put("DELUXE", new Room("DELUXE", 3500,
                Arrays.asList("WiFi", "AC", "TV")));
        roomCatalog.put("SUITE", new Room("SUITE", 7000,
                Arrays.asList("WiFi", "AC", "TV", "Mini Bar")));
        roomCatalog.put("STANDARD", new Room("STANDARD", 2000,
                Arrays.asList("WiFi", "Fan")));

        // Setup Inventory (State Holder)
        Inventory inventory = new Inventory();
        inventory.setAvailability("DELUXE", 3);
        inventory.setAvailability("SUITE", 0);     // Should be filtered out
        inventory.setAvailability("STANDARD", 5);

        // Search Service
        SearchService searchService = new SearchService(inventory, roomCatalog);

        // Guest initiates search
        List<Room> results = searchService.searchAvailableRooms();

        // Display Results
        System.out.println("Available Rooms:");
        for (Room room : results) {
            System.out.println(room);
        }
    }
}