package fjwa.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import fjwa.RMXException;
import fjwa.model.Bomb;
import fjwa.model.Bombs;
import fjwa.service.BombService;

@Controller
@SessionAttributes("bomb")
public class BoomController {

	@Autowired
	private BombService bombService;

	@RequestMapping(value = "/boom") //The page name (.whatever - i.e. html)
	public String sayBoom(Model model) {
		bombService.update();
		this.findAllBombs(model); 
		model.addAttribute("errors", bombService.getErrors());
		return "boom"; //The jsp name?
	}

	@RequestMapping(value = "addBomb", method = RequestMethod.GET)
	public String addBomb(Model model) {//, BindingResult result){
		Bomb bomb = Bombs.newBomb();

		try {
//			bombService.addBomb(bomb);
			bombService.save(bomb);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errors", e.getLocalizedMessage());
		} 
		model.addAttribute("errors", bombService.getErrors());
		return "redirect:boom.html";
	}


	@RequestMapping(value = "addBomb", method = RequestMethod.POST)
	public String updateBomb(@Valid @ModelAttribute("bomb") Bomb bomb, BindingResult result){
		//		Bomb bomb = WeakBomb.newInstance();
		System.out.println("(UPDATE BOMB) result has errors: " + result.hasErrors());

		System.out.println("Timer set: " + bomb.timeRemaining());

		if(result.hasErrors()) {
			
			return "redirect:boom.html";
		} else {
			bombService.save(bomb);
		}
		return "redirect:boom.html";
	}

	@RequestMapping(value = "cleanUp", method = RequestMethod.GET)
	public String cleanUp(Model model){
		try {
			bombService.cleanUp();
		} catch (RMXException e) {
			e.printStackTrace();
		}
		model.addAttribute("errors", bombService.getErrors());
		return "redirect:boom.html";
	}


	@RequestMapping(value = "defuse", method = RequestMethod.GET)
	public String defuse(Model model){
		try {
			bombService.defuse();
			bombService.synchronize();
		} catch (RMXException e) {
			e.printStackTrace();
		}
		model.addAttribute("errors", bombService.getErrors());
		return "redirect:boom.html";
	}


	@RequestMapping(value = "findAllBombs", method = RequestMethod.GET) //bombs?
	//	@Retention(RetentionPolicy.SOURCE)
	public @ResponseBody List<String> findAllBombs(Model model) {
		System.out.println("findAllBombs called");
		Collection<Bomb> bombs = null;
		try {
			bombs = bombService.findAllBombs();
		} catch (RMXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (bombs == null || bombs.isEmpty()) 
				model.addAttribute("boom", "No bombs... safe!");
			else {
				model.addAttribute("boom", "Time is ticking...");

				String result = "";
				for (Bomb bomb : bombs) {
					result += "<br/> " + bomb.toString();
				}

				model.addAttribute("bombs", result);
				return Arrays.asList(result);
			}
		}
		return Arrays.asList("No Bombs");
	}

	@RequestMapping(value = "updateBombs", method = RequestMethod.GET) //bombs?
	public @ResponseBody Collection<Bomb> updateBombs(Model model) {
		//		System.out.println("updateBombs called");
		return bombService.update();
	}



}
