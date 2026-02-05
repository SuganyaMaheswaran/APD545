# **Java ArrayList, Vector, Generics, and List - Cheat Sheet**

## **1\. Thread Safety & Vector**

- **Thread-safe:** Multiple threads can access/modify a data structure without corruption.
- **Vector** is synchronized by default.
- ArrayList is **not thread-safe**; use Collections.synchronizedList() for safety.

## **2. ArrayList vs Vector**
| **Feature** | **ArrayList** | **Vector** |
| --- | --- | --- |
| Thread Safety | Not thread-safe | Thread-safe |
| Performance | Faster | Slower |
| Legacy | Modern | Legacy |
| Default capacity | 10 | 10 |


## **3. Synchronized in Java Lists**

- Ensures only one thread can modify at a time.
- Vector methods are synchronized.
- For ArrayList:

```
List<Integer> safeList = Collections.synchronizedList(new ArrayList<>());
```

## **4. List & Common Operations**

- Interface: List<T>
- Methods: add(), remove(), get(), set(), size(), contains()
- Iteration:

```
for (T element : list) {

System.out.println(element);

}
```

## **5. ArrayList Concurrent Add Issue**

- Multiple threads adding can overwrite or lose data.
- Happens during resizing or simultaneous adds.
- Fix: use Vector or Collections.synchronizedList().

## **6. ArrayList Syntax & Generics**
```
List<String> listOfStrings = new ArrayList<>();

List<Integer> listOfNumbers = new ArrayList<>();

List<Double> listOfDoubles = new ArrayList<>();
```

- Use wrapper classes for primitives: int -> Integer, double -> Double.
- Custom classes work directly.

## **7. Custom Classes in Generics**

class Truck {

String model;

int year;

Truck(String model, int year) { this.model = model; this.year = year; }

}

List<Truck> fleet = new ArrayList<>();

fleet.add(new Truck("Ford", 1990));

- Type-safe: cannot add wrong types.

## **8. List vs ArrayList**

- List = interface; ArrayList = concrete class.
- Declare variables as List<Truck> fleet = new ArrayList<>(); for flexibility.
- Allows swapping implementations (LinkedList, Vector).

## **9. ArrayList of Strings Example**

List<String> cities = new ArrayList<>();

cities.add("California");

cities.add("Toronto");

cities.add("Montreal");

cities.add("London");

cities.add("Tokyo");

for(String city : cities) {

System.out.println(city);

}

## **10. ArrayList of Integers Example**

List<Integer> numbers = new ArrayList<>();

numbers.add(10);

numbers.add(20);

numbers.add(30);

numbers.set(1, 25);

numbers.remove(numbers.size() - 1);

## **11. Custom Class List Example**

class Truck {

String model;

int year;

Truck(String model, int year){ this.model = model; this.year = year; }

public String toString(){ return model + " (" + year + ")"; }

}

List<Truck> fleet = new ArrayList<>();

fleet.add(new Truck("Ford", 1990));

fleet.add(new Truck("Chevy", 2000));

fleet.add(new Truck("Toyota", 2010));

for(Truck t : fleet){

System.out.println(t);

}

## **12\. Remove & Search in List**

fleet.remove(1);

String search = "Toyota";

for(Truck t : fleet){

if(t.model.equals(search)){

System.out.println("Found: " + t);

}

}

## **13\. Thread-Safe Vector Example**

Vector<Truck> fleetVector = new Vector<>();

fleetVector.add(new Truck("Ford", 1990));

- Safe for multiple threads.

## **14\. Array vs ArrayList**

Fleet\[\] fleetArray = new Fleet\[10\];

fleetArray\[0\] = new Fleet("Alpha", 5);

List<Fleet> fleetList = new ArrayList<>();

fleetList.add(new Fleet("Alpha", 5));

- Arrays are fixed size, ArrayList is