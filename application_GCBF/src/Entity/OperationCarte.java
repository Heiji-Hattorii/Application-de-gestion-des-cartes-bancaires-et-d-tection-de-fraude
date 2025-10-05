package Entity;

import java.time.LocalDateTime;

public record OperationCarte(String id,LocalDateTime date,double montant,TypeOperation type,String lieu,String idCarte){}

