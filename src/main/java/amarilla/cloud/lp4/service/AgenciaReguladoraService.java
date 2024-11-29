package amarilla.cloud.lp4.service;

import amarilla.cloud.lp4.model.AgenciaReguladora;
import amarilla.cloud.lp4.model.Esporte;
import amarilla.cloud.lp4.reposotory.AgenciaReguladoraRepository;
import exception.EsporteNotFoundExeption;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgenciaReguladoraService {
    @Autowired
    private AgenciaReguladoraRepository agenciaReguladoraRepository;

    public AgenciaReguladora salvar(AgenciaReguladora agencia){
        return agenciaReguladoraRepository.save(agencia);
    }

    public List<AgenciaReguladora> buscarTodosAgencia(){
        return agenciaReguladoraRepository.findAll();
    }

    public AgenciaReguladora buscarEsportePorId(Long id) throws Exception {
        Optional<AgenciaReguladora> opt = agenciaReguladoraRepository.findById(id);
        if (opt.isPresent()){
            return opt.get();
        }
        throw new Exception("Agencia não encontrado");
    }

    public void apagar(Long id) throws Exception {
        AgenciaReguladora agencia = buscarEsportePorId(id);
        agenciaReguladoraRepository.delete(agencia);
    }

    public AgenciaReguladora alterar(AgenciaReguladora agenciaReguladora){
        return agenciaReguladoraRepository.save(agenciaReguladora);
    }

    public List<AgenciaReguladora> buscarTodasAgenciasNome(String nome){
        return agenciaReguladoraRepository.findByNomeContainingIgnoreCase(nome);
    }

}
