// import React, {Component} from 'react';
// import Header from "./Header";
//
// class AddImage extends Component {
//     render() {
//         return (
//             <div>
//                 <Header/>
//                 BURALARA HEP IMAGE GELECEK
//             </div>
//         );
//     }
// }
//
// export default AddImage;

import React, {useState, useEffect} from 'react';
//import '../App.css';
import Header from "./Header";
import warnAboutDeprecatedESMImport from "react-router-dom/es/warnAboutDeprecatedESMImport";

function AddImages() {
    const [selectedFile, setSelectedFile] = useState();
    const [imageList, setImageList] = useState();

    const onImageChange = event => {
        console.warn("event : ", event)
        if (event.target.files && event.target.files[0]) {
            setSelectedFile(event.target.files[0])
        }
    }

    const onFileUpload = () => {
        if (!selectedFile) {
            alert("file not selected!");
        }

        const data = new FormData();
        data.append('file', selectedFile);
        data.append('imageName', selectedFile.name);

        fetch("http://localhost:8080/file/add", {
            method: 'POST',
            mode: 'no-cors',
            body: data
        }).then(responce => responce.text())
            .then(result => console.warn("result", result))
            .catch(error => console.warn("error", error))

    };

    useEffect(() => {
        var requestOptions = {
            method:'GET',
        };
        fetch("http://localhost:8080/file/list", requestOptions)
            .then(response => response.text())
            .then(result => setImageList(JSON.parse(result)))
            .catch(error => console.log('error',error));
    },[selectedFile]);

    const getFiles = () => {
        if(!imageList){
            return null;
        }

        let list = [];
        console.log(imageList)
        imageList.map(v=> {
            list.push(<li>
                <img src={'data:image/png;base64,' + v.fileContent} width="150" style={{margin:10}}/>
            </li>)
        })
        return (
                <ul>
                    {list}
                </ul>
        )
    }

    return(
        <div className="App">
            <Header/>
            <input type="file" name="file" style={{paddingTop:20}} onChange={(e)=> onImageChange(e)}/>
            <button style={{marginTop:20}} onClick={()=> onFileUpload()}>Upload Image</button>
            {getFiles()}
        </div>
    )

}

export default AddImages;