package com.cjm.ms;

import java.util.HashSet;
import java.util.Set;

public final class HeavenlyBody {
    private final String name;
    private final double orbitalPeriod;
    private final Set<HeavenlyBody> satellites = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        boolean output;
        if (this == o) {
            output = true;
        } else if (o == null || !(o instanceof HeavenlyBody)) {
            output = false;
        } else {
            HeavenlyBody oHB = (HeavenlyBody) o;
            output = name.equals(oHB.getName());
        }
        return output;
    }

    @Override
    public int hashCode() {
        return name.hashCode() + 57;
    }

    public HeavenlyBody(String name, double orbitalPeriod) {
        this.name = name;
        this.orbitalPeriod = orbitalPeriod;
    }

    public String getName() {
        return name;
    }

    public double getOrbitalPeriod() {
        return orbitalPeriod;
    }

    public Set<HeavenlyBody> getSatellites() {
        return new HashSet<>(satellites);
    }

    public boolean addMoon(HeavenlyBody moon) {
        return this.satellites.add(moon);
    }
}
