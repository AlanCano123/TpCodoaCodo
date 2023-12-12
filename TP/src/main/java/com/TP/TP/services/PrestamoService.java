package com.TP.TP.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.TP.TP.repositories.UserRepository;
import  com.TP.TP.models.User;
import com.TP.TP.exceptions.UserNotExistsException;
import  com.TP.TP.models.Prestamo;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import com.TP.TP.repositories.PrestamoRepository;

@Service
public class PrestamoService {
    @Autowired
    private final UserRepository repository;

    @Autowired
    private final PrestamoRepository prestamoRepository;

    public PrestamoService(UserRepository repository, PrestamoRepository prestamoRepository) {
        this.repository = repository;
        this.prestamoRepository = prestamoRepository;
    }

    public Prestamo crearPrestamo(Prestamo prestamo,Long id,int dias){
    Optional<User> user = repository.findById(id);
    if(user.isEmpty()){
        throw new UserNotExistsException("este user no existe");
    }
    prestamo.setDescripcion("Prestamo de rodado");
    prestamo.setMonto(calculoIntereses(prestamo.getMonto(), dias));
    prestamo.setVencimiento(LocalDate.now().plusDays(dias));
    prestamo.setUser(user.get());
    user.get().setPrestamo(prestamo);
    prestamoRepository.save(prestamo);
    repository.save(user.get());
    return prestamo;
    }

    public double calculoIntereses(double monto,int dias){
        double montoFinal;
        double calculo;
        if(dias<30){
            calculo = (monto * dias * 0.020);
        }else{
            calculo = (monto * dias * 0.030);
        }
        montoFinal=monto + calculo;
        return montoFinal;
    }

    public List<Prestamo> traer(){
        return prestamoRepository.findAll();
    }
}
