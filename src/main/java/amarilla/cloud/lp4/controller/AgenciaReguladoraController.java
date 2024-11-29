package amarilla.cloud.lp4.controller;

import amarilla.cloud.lp4.model.AgenciaReguladora;
import amarilla.cloud.lp4.model.Esporte;
import amarilla.cloud.lp4.service.AgenciaReguladoraService;
import exception.EsporteNotFoundExeption;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AgenciaReguladoraController {
    @Autowired
    private AgenciaReguladoraService agenciaReguladoraService;
    @GetMapping("/agencias")
    public String listaAgencias(Model model) {
        List<AgenciaReguladora> agencia = agenciaReguladoraService.buscarTodosAgencia();
        model.addAttribute("listaEsportes", agencia);

        return "/lista-agencias";
    }

    @GetMapping("/agencias/novo")
    public String novoAgencia(Model model) {
        AgenciaReguladora agencia = new AgenciaReguladora();
        model.addAttribute("novoAgencia", agencia);
        return "/novo-agencia";
    }

    @PostMapping("/agencias/gravar")
    public String gravarAgencia(@ModelAttribute("novoEsporte") @Valid AgenciaReguladora agencia, BindingResult err, RedirectAttributes attributes) {
        if (err.hasErrors()) {
            attributes.addFlashAttribute("err", "erro ao salvar agencia");
            return "redirect:/agencias/novo";
        }

        agenciaReguladoraService.salvar(agencia);
        attributes.addFlashAttribute("mensagem", "salvo com sucesso");
        return "redirect:/agencias/novo";
    }

    @GetMapping("/agencias/apagar/{id}")
    public String apagarAgencias(@PathVariable("id") long id, RedirectAttributes attributes) {
        try {
            agenciaReguladoraService.apagar(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "redirect:/";
    }

    @GetMapping("/agencias/editar/{id}")
    public String editarAgencias(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
        try {
            AgenciaReguladora agencia = agenciaReguladoraService.buscarEsportePorId(id);
            model.addAttribute("objAgencia",agencia);
            return "editar-agencia";
        } catch (Exception e) {
            return "redirect:/";
        }


    }


    @PostMapping("/agencia/buscar")
    public String buscarAgencia(Model model, @Param("nome") String nome) {
        if (nome == null) {
            return "redirect:/";
        }
        List<AgenciaReguladora> agencias = agenciaReguladoraService.buscarTodasAgenciasNome(nome);
        model.addAttribute("listaAgencias",agencias);
        return "/lista-agencias";
    }


}
