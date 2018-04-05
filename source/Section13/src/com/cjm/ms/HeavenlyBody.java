/* Exercise:
Modify the previous HeavenlyBody example so that the HeavenlyBody
class also has a "bodyType" field. This field will store the
type of HeavenlyBody (such as STAR, PLANET, MOON, etc).

You can include as many types as you want, but must support at
least PLANET and MOON.

For each of the types that you support, subclass the HeavenlyBody class
so that your program can create objects of the appropriate type.

Although astronomers may shudder at this, our solar systems will
allow two bodies to have the same name as long as they are not the
same type of body: so you could have a star called "BetaMinor" and
an asteroid also called "BetaMinor", for example.

Hint: This is much easier to implement for the Set than it is for the Map,
because the Map will need a key that uses both fields.

There is a restriction that the only satellites that planets can have must
be moons. Even if you don't implement a STAR type, though, your program
should not prevent one being added in the future (and a STAR's satellites
can be almost every kind of HeavenlyBody).

Test cases:
1. The planets and moons that we added in the previous video should appear in
the solarSystem collection and in the sets of moons for the appropriate planets.

2. a.equals(b) must return the same result as b.equals(a) - equals is symmetric.

3. Attempting to add a duplicate to a Set must result in no change to the set (so
the original value is not replaced by the new one).

4. Attempting to add a duplicate to a Map results in the original being replaced
by the new object.

5. Two bodies with the same name but different designations can be added to the same set.

6. Two bodies with the same name but different designations can be added to the same map,
and can be retrieved from the map.
*/

package com.cjm.ms;

import java.util.HashSet;
import java.util.Set;

public class HeavenlyBody {
    private final String name;
    private final BodyTypes bodyType;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites = new HashSet<>();

    public enum BodyTypes {
        STAR,
        PLANET,
        DWARF_PLANET,
        MOON,
        COMET,
        ASTEROID
    }

    public static String makeKey(String name, BodyTypes bodyType) {
        return name + bodyType;
    }

    // Marking method as final prevents subclasses from altering important behaviour.
    @Override
    public final boolean equals(Object o) {
        boolean output;
        if (this == o) {
            output = true;
        } else if (o == null || !(o instanceof HeavenlyBody)) {
            output = false;
        } else {
            HeavenlyBody oHB = (HeavenlyBody) o;
            output = name.equals(oHB.getName()) && bodyType.equals(oHB.getBodyType());
        }
        return output;
    }

    /* Used by hash tables to sort objects into buckets. This makes them faster to retrieve as you can
    skip to the correct bucket based on the hash code and avoid searching through each element. Objects
    that are equal must return the same hash code otherwise they will never be matched. Different objects
    with the same hash code are allowed, but will lower efficiency as the algorithm must check each object
    within that bucket for equality.
    * */
    @Override
    public final int hashCode() {
        return getKey().hashCode() + 57;
    }

    @Override
    public String toString() {
        return name + ", " + bodyType + ", " + orbitalPeriod;
    }

    public HeavenlyBody(String name, BodyTypes bodyType, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
        this.bodyType = bodyType;
    }

    public String getName() {
        return name;
    }

    public BodyTypes getBodyType() {
        return bodyType;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(satellites);
    }

    public String getKey() {
        return makeKey(name, bodyType);
    }

    public boolean addSatellite(HeavenlyBody satellite) {
        // Bodies orbit bodies of similar mass or larger.
        return satellite.getBodyType().compareTo(bodyType) >= 0 &&
                satellites.add(satellite);
    }
}

class StarBody extends HeavenlyBody {
    public StarBody(String name, double orbitalPeriod) {
        super(name, BodyTypes.STAR, orbitalPeriod);
    }
}

class PlanetBody extends HeavenlyBody {

    // Redundant due to mass logic in HeavenlyBody.addSatellite().
    @Override
    public boolean addSatellite(HeavenlyBody satellite) {
        return satellite.getBodyType().equals(BodyTypes.MOON) && super.addSatellite(satellite);
    }

    public PlanetBody(String name, double orbitalPeriod) {
        super(name, BodyTypes.PLANET, orbitalPeriod);
    }
}

class MoonBody extends HeavenlyBody {
    public MoonBody(String name, double orbitalPeriod) {
        super(name, BodyTypes.MOON, orbitalPeriod);
    }
}