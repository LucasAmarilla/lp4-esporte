package amarilla.cloud.lp4.service;

import amarilla.cloud.lp4.model.Esporte;
import amarilla.cloud.lp4.reposotory.EsporteRepository;
import exception.EsporteNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EsporteService {
    @Autowired
    private EsporteRepository esporteRepository;

    public Esporte salvar(Esporte esporte){
        return esporteRepository.save(esporte);
    }

    public List<Esporte> buscarTodosEsportes(){
        return esporteRepository.findAll();
    }

    public Esporte buscarEsportePorId(Long id) throws EsporteNotFoundExeption {
        Optional<Esporte> opt = esporteRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new EsporteNotFoundExeption("Estudante não encontrado");
    }

    public void apagar(Long id) throws EsporteNotFoundExeption {
        Esporte esporte = buscarEsportePorId(id);
        esporteRepository.delete(esporte);
    }

    public Esporte alterar(Esporte esporte){
        return esporteRepository.save(esporte);
    }

    public List<Esporte> buscarTodosEsportesNome(String nome){
        return esporteRepository.findByNomeContainingIgnoreCase(nome);
    }

}
