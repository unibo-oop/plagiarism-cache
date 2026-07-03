package model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
/**
 * Hospital Implementation.
 */
public class Hospital implements Model, Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 5482636455417647870L;

    private final List<Patient> waitingRoom;
    private final List<Doctor> doctors;
    private final List<Ward> wards;
    private final List<OperatingRoom> operatingRooms;
    /**
     * default constructor.
     */
    public Hospital() {
        this.waitingRoom = new ArrayList<>();
        this.doctors = new ArrayList<>();
        this.wards = new ArrayList<>();
        this.operatingRooms = new ArrayList<>();
    }

    @Override
    public void addPatient(final Patient p) throws IllegalStateException {
        wards.forEach(wd -> {
            if (wd.getPatients().contains(p)) {
                throw new IllegalStateException("Patient already exists inside of the Hospital");
            }
        });
        waitingRoom.forEach(pt -> {
            if (pt.equals(p)) {
                throw new IllegalStateException("Patient already exists inside of the Waiting Room");
            }
        });
        waitingRoom.add(p);
    }


    @Override
    public void removePatient(final Patient p) {
        operatingRooms.forEach(or -> { 
            if (p.equals(or.getPatient())) {
            throw new IllegalStateException("Patient is still in surgery");
            }
        });
        if (waitingRoom.contains(p)) {
            waitingRoom.remove(p);
        } else {
            doctors.forEach(d -> {
                if (d.getAssignedPatients().contains(p)) {
                    d.dismissPatient(p);
                }
            });
            wards.forEach(w -> {
                if (w.getPatients().contains(p)) {
                    w.removePatient(p);
                }
            });
        }
    }

    @Override
    public void addDoctor(final Doctor d) throws IllegalStateException {
        doctors.forEach(dc -> {
            if (dc.equals(d)) {
                throw new IllegalStateException("Doctor already exists");
            }
        });
        doctors.add(d);
    }

    @Override
    public void removeDoctor(final Doctor d) throws IllegalStateException {
        if (!d.getAssignedPatients().isEmpty()) {
            throw new IllegalStateException("Doctor still has assigned patients");
        }
        doctors.remove(d);
    }

    @Override
    public void addWard(final Ward w) throws IllegalStateException {
        if (wards.contains(w)) {
            throw new IllegalStateException("Ward already exists");
        }
        wards.add(w);
    }

    @Override
    public void removeWard(final Ward w) throws IllegalStateException {
        if (!wards.contains(w)) {
            throw new IllegalStateException("Ward does not exist");
        }
        wards.remove(w);
    }

    @Override
    public void treatPatient(final Patient p, final Doctor d, final Ward w) throws IllegalStateException {
        if (!wards.contains(w)) {
            throw new IllegalStateException("Illegal Ward");
        }
        if (!waitingRoom.contains(p)) {
            throw new IllegalStateException("Illegal Patient");
        }
        if (!doctors.contains(d)) {
            throw new IllegalStateException("Illegal Doctor");
        }
        p.setWard(w);
        p.setDoctor(d);
        wards.forEach(wd -> {
            if (wd.equals(w)) {
                wd.enterPatient(p);
            }
        });
        doctors.forEach(dc -> {
            if (dc.equals(d)) {
                dc.assignPatient(p);
            }

        });
        waitingRoom.remove(p);
    }

    @Override
    public List<Patient> getAllPatients() throws IllegalStateException {
        final List<Patient> l = new ArrayList<>();
        wards.forEach(wd -> l.addAll(wd.getPatients()));
        if (l.isEmpty()) {
            throw new IllegalStateException("No Patients in the Hospital");
        }
        return l;
    }

    @Override
    public List<Patient> getWaitingPatients() throws IllegalStateException {
        if (waitingRoom.isEmpty()) {
            throw new IllegalStateException("No Patients in the Waiting Room");
        }
        return waitingRoom.stream()
                          .sorted((p1, p2) -> p2.getPriorityLevel() - p1.getPriorityLevel())
                          .collect(Collectors.toList());
    }

    @Override
    public List<Doctor> getAllDoctors() throws IllegalStateException {
        if (doctors.isEmpty()) {
            throw new IllegalStateException("No Doctors in the Hospital");
        }
        return doctors;
    }

    @Override
    public List<Ward> getAllWards() throws IllegalStateException {
        if (wards.isEmpty()) {
            throw new IllegalStateException("No Wards in the Hospital");
        }
        return wards;
    }

    @Override
    public void addOperatingRoom(final OperatingRoom room) throws IllegalStateException {
      operatingRooms.forEach(or -> {
        if (or.getName().equals(room.getName())) {
            throw new IllegalStateException("OperatingRoom already exists");
        }
      });
      operatingRooms.add(room);
    }

    @Override
    public void removeOperatingRoom(final OperatingRoom r) throws IllegalStateException {
      if (!operatingRooms.contains(r)) {
        throw new IllegalStateException("OperatingRoom does not exist");
      }
      operatingRooms.remove(r);
    }

    @Override
    public void startSurgery(final Patient p, final OperatingRoom r) throws IllegalStateException {
        if (!p.getWard().getPatients().contains(p)) {
            throw new IllegalStateException("Patient does not exist");
        }
        if (!operatingRooms.contains(r)) {
            throw new IllegalStateException("OperatingRoom does not exist");
        }
        operatingRooms.forEach(or -> {
            if (or.getName().equals(r.getName())) {
                or.addPatient(p);
            }
        });
    }

    @Override
    public void endSurgery(final OperatingRoom r) throws IllegalStateException {
      if (!operatingRooms.contains(r)) {
          throw new IllegalStateException("OperatingRoom does not exist");
      }
      operatingRooms.forEach(or -> {
          if (or.getName().equals(r.getName())) {
              or.removePatient();
          }
      });
    }


    @Override
    public List<OperatingRoom> getAllOperatingRooms() throws IllegalStateException {
        if (operatingRooms.isEmpty()) {
            throw new IllegalStateException("There are no OperatingRoom");
        }
        return operatingRooms;
    }

    @Override
    public List<OperatingRoom> getFreeOperatingRooms() throws IllegalStateException {
        final List<OperatingRoom> list = new ArrayList<>();
        operatingRooms.forEach(or -> {
            if (or.getPatient() == null) {
                list.add(or);
            }
        });
        if (list.isEmpty()) {
            throw new IllegalStateException("There are no free OperatingRoom");
        }
        return list;
    }

    @Override
    public void updatePatientLog(final Patient p, final String log) throws IllegalStateException  {
        wards.forEach(w -> {
            if (!w.getPatients().contains(p)) {
                throw new IllegalStateException("Patient does not exist");
            } 
        });
        wards.stream()
             .filter(wd -> wd.getOccupiedRooms().containsValue(p))
             .findAny().get().getOccupiedRooms().values()
             .forEach(pt -> {
                 if (pt.equals(p)) {
                     pt.updateLog(log);
                 }
             });
    }

    @Override
    public void serializeModel(final ObjectOutputStream out) throws IOException {
        out.writeObject(this);
    }

    @Override
    public Hospital deserializeModel(final ObjectInputStream in) throws ClassNotFoundException, IOException {
        return (Hospital) in.readObject();

    }

}
