import React, { useState } from 'react';
import axios from "axios";

const Uploader = () => {

    const [file, setFile] = useState({})

    const onUploadFile = e => {
        setFile(e.target.files[0]);
    }

    const onSubmitFile = () => {

        console.log(file)

        const formData = new FormData();
        formData.append("file", file, file.name);
        axios.post("http://localhost:8081/api/v1/upload", formData)
            .then(response => console.log(response))
    }

    return (
        <div>
            <input type="file" onChange={onUploadFile}/>
            <button onClick={onSubmitFile}>upload</button>
        </div>
    );
};


export default Uploader;