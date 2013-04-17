package web.controller;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import web.common.StaticMap;
import web.model.Comment;
import web.model.Property;
import web.model.PropertyOptions;
import web.service.CommentService;
import web.service.PropertyOptionsService;
import web.service.PropertyService;

/**
 *
 * @author Bernard <bernard.debecker@gmail.com>
 */
@Controller
public class PropertyController {
    
    @Autowired
    private PropertyService propertyService;
    @Autowired
    private PropertyOptionsService propertyOptionsService;
    @Autowired
    private CommentService comService;
    
    @RequestMapping(value = "/property/{id}", method = RequestMethod.GET)
    public String propertyView(@PathVariable Integer id, Model model) {

        // Get Property
        Property property = propertyService.findById(id);
        PropertyOptions options = propertyOptionsService.findByProperty(property);
        List<Comment> comments = comService.findByProperty(property);
        Integer evaluation = property.getNote();
        String pathMap;
        pathMap = StaticMap.buildMapURL(property);
        List<String> pictures = new LinkedList<String>();
        for (int i = 1; i < 4; i++) {
            pictures.add(new Integer(i).toString() + ".jpg");
        }

        model.addAttribute("property", property);
        model.addAttribute("options", options);
        model.addAttribute("evaluation", evaluation);
        model.addAttribute("comments", comments);
        model.addAttribute("map", pathMap);
        model.addAttribute("pictures", pictures);

        return "property";
    }
    
    @RequestMapping(value = "/property/new", method = RequestMethod.GET)
    public String newView(Model model) {
        String test = "LEFT";
        Property property = new Property();
        
        model.addAttribute("test", test);
        model.addAttribute("property", property);

        return "new";
    }
    
    @RequestMapping(value="/s/property/add", params={"save"})
    public String subscribe(final Property property, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            return "new";
        }
//        handle new property
//        String id = getnewpropertyid and add it to the redirect path
        return "redirect:/property/";
    }

}
