import React, { useEffect, useState } from "react";
import {
  Button,
  Container,
  FormControl,
  FormControlLabel,
  FormLabel,
  makeStyles,
  Radio,
  RadioGroup,
  TextField,
  Typography,
} from "@material-ui/core";
import KeyboardArrowRightIcon from "@material-ui/icons/KeyboardArrowRight";
import { useRouter } from "next/router";
import store from "../store/configStore";
import { useDispatch, useSelector } from "react-redux";

const useStyles = makeStyles({
  field: {
    marginTop: 20,
    marginBottom: 20,
    display: "block",
  },
});

const Create = () => {
  const classes = useStyles();
  const router = useRouter();
  const [title, setTitle] = useState("");
  const [details, setDetails] = useState("");
  const [titleError, setTitleError] = useState(false);
  const [detailsError, setDetailsError] = useState(false);
  const [category, setCategory] = useState("todos");

  const dispatch = useDispatch();
  const todos = useSelector((state) => state.todos);

  useEffect(() => {
    if (todos.msg == "SUCCESS") {
      router.push("/");
    }
  }, [todos]);

  const handleSubmit = (e) => {
    e.preventDefault();

    setTitleError(title == "");
    setDetailsError(details == "");

    if (title && details) {
      dispatch({
        type: "SUBMIT",
        payload: { title, details, category },
      });

      // fetch("http://localhost:8000/notes", {
      //   method: "POST",
      //   headers: { "Content-type": "application/json" },
      //   body: JSON.stringify({ title, details, category }),
      // }).then(() => router.push("/"));
    }
  };

  return (
    <Container>
      <Typography
        variant="h6"
        color="textSecondary"
        component="h2"
        gutterBottom
      >
        Create a New Note
      </Typography>

      <form noValidate autoComplete="off" onSubmit={handleSubmit}>
        <TextField
          onChange={(e) => setTitle(e.target.value)}
          variant="outlined"
          label="Not Title"
          color="secondary"
          fullWidth
          required
          error={titleError}
          className={classes.field}
        ></TextField>
        <TextField
          onChange={(e) => setDetails(e.target.value)}
          variant="outlined"
          label="Details"
          color="secondary"
          multiline
          rows={4}
          fullWidth
          required
          error={detailsError}
          className={classes.field}
        ></TextField>
        <FormControl className={classes.field}>
          <FormLabel>Note Category</FormLabel>
          <RadioGroup
            value={category}
            onChange={(e) => setCategory(e.target.value)}
          >
            <FormControlLabel
              control={<Radio />}
              label="Category"
              value="money"
            />
            <FormControlLabel control={<Radio />} label="Todos" value="todos" />
            <FormControlLabel
              control={<Radio />}
              label="Reminders"
              value="reminders"
            />
            <FormControlLabel control={<Radio />} label="Work" value="work" />
          </RadioGroup>
        </FormControl>

        <Button
          type="submit"
          color="secondary"
          variant="contained"
          endIcon={<KeyboardArrowRightIcon />}
        >
          Submit
        </Button>
      </form>
    </Container>
  );
};

Create.propTypes = {};

export default Create;
