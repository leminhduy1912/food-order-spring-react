import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  Button,
  Checkbox,
  FormControlLabel,
  FormGroup,
} from "@mui/material";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import Fade from "@mui/material/Fade";
import { useState } from "react";
const MenuCard = () => {
  const [expanded, setExpanded] = useState(false);

  const handleExpansion = () => {
    setExpanded((prevExpanded) => !prevExpanded);
  };
  const handleCheckBoxChange = () => {};
  const ingredients = [
    {
      category: "Nuts & seeds",
      ingredients: ["Cashews"],
    },
    {
      category: "Protein",
      ingredients: ["Ground beef", "Bacon Strips"],
    },
  ];
  return (
    <div>
      <Accordion
        expanded={expanded}
        onChange={handleExpansion}
        slots={{ transition: Fade }}
        slotProps={{ transition: { timeout: 400 } }}
        sx={{
          "& .MuiAccordion-region": { height: expanded ? "auto" : 0 },
          "& .MuiAccordionDetails-root": {
            display: expanded ? "block" : "none",
          },
        }}
      >
        <AccordionSummary
          expandIcon={<ExpandMoreIcon />}
          aria-controls="panel1-content"
          id="panel1-header"
        >
          <div className="lg:flex items-center justify-center">
            <div className="lg:flex items-center lg:gap-5">
              <img
                className="h-[7rem] w-[7rem] object-cover"
                src="https://th.bing.com/th/id/OIP.Z34VvdCT6bEordzT-7HgqwHaF-?w=220&h=180&c=7&r=0&o=5&dpr=1.3&pid=1.7"
                alt=""
              />
              <div className="space-y-1 lg:space-y-5 lg:max-w-2xl">
                <p className="font-semibold text-xl">Burger</p>
                <p>499</p>
                <p className="text-gray-400">nice food</p>
              </div>
            </div>
          </div>
        </AccordionSummary>
        <AccordionDetails>
          <form action="">
            <div className="flex gap-5 flex-wrap">
              {ingredients.map((item, index) => {
                return (
                  <div key={index}>
                    <p>{item.category}</p>
                    {item.ingredients.map((item, index) => {
                      return (
                        <FormGroup key={index}>
                          <FormControlLabel
                            control={
                              <Checkbox
                                onChange={() => {
                                  handleCheckBoxChange(item);
                                }}
                              />
                            }
                            label={item}
                          />
                        </FormGroup>
                      );
                    })}
                  </div>
                );
              })}
            </div>
            <div className="pt-55">
              <Button type="submit" variant="contained" disabled={false}>
                {true ? "Add to cart" : "Out of stock"}
              </Button>
            </div>
          </form>
        </AccordionDetails>
      </Accordion>
    </div>
  );
};

export default MenuCard;
