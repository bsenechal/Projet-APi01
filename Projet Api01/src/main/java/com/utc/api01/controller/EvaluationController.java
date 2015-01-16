package com.utc.api01.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.utc.api01.model.Evaluation;
import com.utc.api01.model.Question;
import com.utc.api01.service.GeneriqueService;

@Controller
public class EvaluationController {
    private GeneriqueService<Evaluation> evalService;
    private GeneriqueService<Question> questionService;
    
    @Autowired(required = true)
    @Qualifier(value = "evalService")
    public void setEvaluationService(GeneriqueService<Evaluation> e) {
        this.evalService = e;
    }

    @Autowired(required = true)
    @Qualifier(value = "questionService")
    public void setQuestionService(GeneriqueService<Question> q) {
        this.questionService = q;
    }

    @RequestMapping(value = "/admin/evaluations", method = RequestMethod.GET)
    public String listEvaluations(Model model) {
        model.addAttribute("listEvals", this.evalService.list());
        return "evaluation";
    }

    @RequestMapping("/admin/evaluation/remove/{idEval}")
    public String removeEval(@PathVariable("idEval") int id) {

        this.evalService.remove(id);
        return "redirect:/admin/evaluation";
    }

    @RequestMapping(value = "/admin/questions", method = RequestMethod.GET)
    public String listQuestions(Model model) {
        model.addAttribute("listQuestions", this.questionService.list());
        return "question";
    }

    @RequestMapping("/admin/questions/remove/{idQuest}")
    public String removeQuestion(@PathVariable("idQuest") int id) {

        this.questionService.remove(id);
        return "redirect:/admin/question";
    }

    @RequestMapping(value = "/admin/questions/addQuestion", method = RequestMethod.GET)
    public String addUser(Model model) {
        model.addAttribute("question", new Question());
        model.addAttribute("url", "addQuestion/save");

        return "editQuestion";
    }

    @RequestMapping(value = "/admin/questions/addQuestion/save", method = RequestMethod.POST)
    public ModelAndView saveQuestion(@ModelAttribute("question") Question q) {
        ModelAndView model = new ModelAndView();

        model = validateQuestion(q, model, true);

        return model;
    }

    @RequestMapping("/admin/questions/edit/{idQuestion}")
    public String editQuestion(@PathVariable("idQuestion") int id, Model model) {
        model.addAttribute("question", this.questionService.getById(id));
        model.addAttribute("url", "save");
        return "editQuestion";
    }

    @RequestMapping(value = "/admin/questions/edit/save", method = RequestMethod.POST)
    public ModelAndView saveEditQuestion(@ModelAttribute("question") Question q) {
        ModelAndView model = new ModelAndView();

        model = validateQuestion(q, model, false);

        return model;
    }

    private ModelAndView validateQuestion(Question q, ModelAndView model,
            boolean add) {
        model.setViewName("editQuestion");
//        IntegerValidator integerValidator = new IntegerValidator();
//
//        if (q.getLibelle() == null || q.getPonderation() == null
//                || q.getValMax() == null) {
//            model.addObject("error", "Vous devez remplir tous les champs !");
//        } 
//        else if (!integerValidator.isInRange(q.getValMax(), 0, 10)) {
//            model.addObject("error",
//                    "La valeur de la note doit être comprise entre 0 et 10");
//        } else if (!integerValidator.isInRange(q.getPonderation(), 0, 10)) {
//            model.addObject("error",
//                    "La valeur de l'importance de la question doit être comprise entre 0 et 10");
//        } else {
//            if (add) {
//                this.questionService.add(q);
//                model.addObject("msg", "La question a correctement été ajoutée");
//            } else {
//                this.questionService.update(q);
//                model.addObject("msg",
//                        "La question a correctement été modifiée");
//            }
//            model.setViewName("admin");
//        }

        return model;
    }

}
