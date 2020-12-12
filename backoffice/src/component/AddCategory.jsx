import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import {Modal} from "react-bootstrap";
import Dropdown from 'react-bootstrap/Dropdown'
import DropdownButton from 'react-bootstrap/DropdownButton'
import '../App.css'

class AddCategory extends Component {
    state = {
        name: "",
        description: "",
        imageToUrl: "",
        mediaList: [],
        selectedMediaName: "Chose Media",
        selectedMediaUrl: "",
        selectedMediaID:"",
        showModal: false,

    }

    componentDidMount() {
        let uri = "http://localhost:8080/file/list";
        fetch(uri, {
            method: 'get',
            headers: new Headers({
                'Authorization': sessionStorage.getItem('token'),
            }),
        })
            .then(response => response.json())
            .then(data => {
                this.setState({
                    mediaList: data
                })
            })
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCategory = () => {
        const {name, description, imageToUrl,selectedMediaID} = this.state;

        const newMedia = {
            id: selectedMediaID
        }

        const newCategory = {
            name: name,
            description: description,
            imageToUrl: imageToUrl,
            products: [],
            media: newMedia
        }

        axios.post("http://localhost:8080/category/add-category", newCategory,
            {headers: {Authorization: sessionStorage.getItem('token')}});
    }

    onClickItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaUrl:e.fileContent,
            selectedMediaID:e.id
        })
    }

    showMedia = () => {
        this.setState({
            showModal:!this.state.showModal
        })
    }

    handleModal = () => {
        this.setState({
            showModal:!this.state.showModal
        })
    }

    render() {
        const {name, description, imageToUrl, mediaList, selectedMediaName} = this.state;
        return (
            <div>
                <Header/>

                <Modal show={this.state.showModal} onHide={() => this.handleModal()}>
                    <Modal.Header closeButton>Image</Modal.Header>
                    <Modal.Body align="center">
                        {
                            <img src={'data:image/png;base64,' + this.state.selectedMediaUrl} width="250" style={{margin:10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal</button>
                    </Modal.Footer>
                </Modal>

                <div className="col-md-5 mr-auto mb-4 mt-4">
                    <div className="card">
                        <div className="card-header">
                            <h4>Add Category</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addCategory}>
                                <div className="form-group">
                                    <label htmlFor="name">Category Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Category Name"
                                           name="name"
                                           id="nameInput"
                                           value={name}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="description">Description Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Description Name"
                                           name="description"
                                           id="descInput"
                                           value={description}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="imageToUrl">Category Image Url</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Image Url"
                                           name="imageToUrl"
                                           id="descInput"
                                           value={imageToUrl}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div>
                                    <DropdownButton id="dropdown-basic-button" title={selectedMediaName}>
                                        {
                                            mediaList.map(v => {
                                                return (
                                                    <Dropdown.Item onClick={this.onClickItem.bind(this, v)}>{v.name}</Dropdown.Item>
                                                )
                                            })
                                        }
                                    </DropdownButton>
                                </div>

                                <button className="btn btn-link ml-2" onClick={()=>this.showMedia()}>Show Media</button>
                                <button className="btn btn-warning btn-block" type="submit">Add Category</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default AddCategory;