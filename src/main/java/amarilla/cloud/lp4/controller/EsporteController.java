package amarilla.cloud.lp4.controller;

import amarilla.cloud.lp4.model.Esporte;
import amarilla.cloud.lp4.service.EsporteService;
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
public class EsporteController {
    @Autowired
    private EsporteService esporteService;

    @GetMapping("/")
    public String listaEsportes(Model model) {
        List<Esporte> esportes = esporteService.buscarTodosEsportes();
        model.addAttribute("listaEsportes", esportes);

        return "/lista-esportes";
    }

    @GetMapping("/novo-esporte")
    public String novoEsporte(Model model) {
        Esporte esporte = new Esporte();
        model.addAttribute("novoEsporte", esporte);
        return "/novo-esporte";
    }

    @PostMapping("/gravar")
    public String gravar(@ModelAttribute("novoEsporte") @Valid Esporte esporte, BindingResult err, RedirectAttributes attributes) {
        if (err.hasErrors()) {
            return "/novo-esporte";
        }

        esporteService.salvar(esporte);

        attributes.addFlashAttribute("mensagem", "salvo com sucesso");
        return "redirect:/novo-esporte";
    }

    @GetMapping("/apagar/{id}")
    public String apagarEstudante(@PathVariable("id") long id, RedirectAttributes attributes) {
        try {
            esporteService.apagar(id);
        } catch (EsporteNotFoundExeption e) {
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
        }
        return "redirect:/";
    }

    @GetMapping("/editar/{id}")
    public String editarEstudante(@PathVariable("id") long id, RedirectAttributes attributes, Model model) {
        try {
            Esporte esporte = esporteService.buscarEsportePorId(id);
            model.addAttribute("objEsporte",esporte);
            return "editar-esporte";
        } catch (EsporteNotFoundExeption e) {
            attributes.addFlashAttribute("mensagemErro", e.getMessage());
            return "redirect:/";

        }
    }

    @PostMapping("/editar/{id}")
    public String editar(@PathVariable("id") long id, @ModelAttribute("objEsporte") @Valid Esporte esporte, BindingResult err, RedirectAttributes attributes) {
        if (err.hasErrors()) {
            esporte.setId(id);
            return "/editar-esporte";
        }
        esporteService.alterar(esporte);
        attributes.addFlashAttribute("mensagem", "alterado o " +id);
        return "redirect:/";
    }

    @PostMapping("/buscar")
    public String buscarEstudantes(Model model, @Param("nome") String nome) {
        if (nome == null) {
            return "redirect:/";
        }
        List<Esporte> esportes = esporteService.buscarTodosEsportesNome(nome);
        model.addAttribute("listaEsportes",esportes);
        return "/lista-esportes";
    }

}
