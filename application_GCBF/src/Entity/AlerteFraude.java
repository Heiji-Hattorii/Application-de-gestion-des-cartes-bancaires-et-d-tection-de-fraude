package Entity;

public record AlerteFraude(String id,String description,NiveauAlerte niveau,String idCarte) {}

enum NiveauAlerte {
    INFO,
    AVERTISSEMENT,
    CRITIQUE
}
