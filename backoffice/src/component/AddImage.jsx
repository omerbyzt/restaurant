import React, {useState, useEffect} from 'react';
//import '../App.css';
import Header from "./Header";
import Table from "react-bootstrap/Table";
import {Link} from "react-router-dom";

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

    // const getFiles = () => {
    //     if(!imageList){
    //         return null;
    //     }
    //
    //     let list = [];
    //     console.log(imageList)
    //     imageList.map(v=> {
    //         list.push(<li>
    //             <img src={'data:image/png;base64,' + v.fileContent} width="150" style={{margin:10}}/>
    //         </li>)
    //     })
    //     return (
    //             <ul>
    //                 {list}
    //             </ul>
    //     )
    // }

    return(
        <div className="App">
            <Header/>
            <div className="col-md-6 mx-auto mt-5">
                <div className="card">
                    <div className="card-header">
                        <h4>Image Page</h4>
                    </div>
                    <div className="card-body imageCardBody" align="center">
                        <input type="file" name="file"  onChange={(e)=> onImageChange(e)}/>
                        <br/>

                        <Link to="/home"><button className="btn btn-success mb-1 imageUpload" style={{marginTop:20}} onClick={()=> onFileUpload()}>Upload Image</button></Link>


                        <hr/>
                        <Table striped bordered hover>
                            <thead>
                                <tr>
                                    <th>Image Name</th>
                                    <th>Image</th>
                                </tr>
                            </thead>
                            <tbody>
                            {
                                imageList ?
                                imageList.map(v=> {
                                    return(
                                        <tr align="center">
                                            <td>{v.name}</td>
                                            <td>
                                                <img src={'data:image/png;base64,' + v.fileContent} width="100" style={{margin:10}}/>
                                            </td>
                                        </tr>
                                    )
                                }):null
                            }
                            </tbody>
                        </Table>

                        {/*{getFiles()}*/}
                    </div>
                </div>
            </div>

        </div>
    )

}

export default AddImages;