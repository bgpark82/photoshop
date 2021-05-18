import React from 'react';
import { Avatar, Card, CardContent, CardHeader, IconButton, makeStyles, Typography } from "@material-ui/core";
import { DeleteOutlined } from "@material-ui/icons";
import { green, pink, yellow } from "@material-ui/core/colors";

const useStyle = makeStyles({
    avatar: {
        background: (note) => {
            if(note.category == 'work') {
                return yellow[700]
            }
            if(note.category == 'money') {
                return green[500]
            }
            if(note.category == 'todos') {
                return pink[500]
            }
            return blue[500]
        }
    }
})

const NoteCard = ({note, handleDelete}) => {

    const classes = useStyle(note)

    return (
        <Card elevation={1} className={classes.test}>
            <CardHeader
                avatar={
                    <Avatar
                        className={classes.avatar}
                    >{note.category[0].toUpperCase()}</Avatar>
                }
                title={note.title}
                subheader={note.category}
                action={
                    <IconButton onClick={() => handleDelete(note.id)}>
                        <DeleteOutlined/>
                    </IconButton>
                }
            />
            <CardContent>
                <Typography
                    variant="body2"
                    color="textSecondary"
                >
                    {note.details}
                </Typography>
            </CardContent>
        </Card>
    );
};

NoteCard.propTypes = {

};

export default NoteCard;