import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import {Modal} from "react-bootstrap";
import '../App.css'
import UserContext from "../Context";
import Loading from "./Loading";

class AddCategory extends Component {
    static contextType = UserContext;
    state = {
        name: "",
        description: "",
        imageToUrl: "",
        mediaList: [],
        selectedMediaName: "Chose Media",
        selectedMediaUrl: "",
        selectedMediaID: "",
        showModal: false,
        loadingIsVisible: false
    }

    async componentDidMount() {
        const {token} = this.context
        this.setState({loadingIsVisible: true});
        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/file/list";
            await fetch(uri, {
                method: 'get',
                headers: new Headers({
                    // 'Authorization': sessionStorage.getItem('token'),
                    'Authorization': token,
                }),
            })
                .then(response => response.json())
                .then(data => {
                    this.setState({
                        mediaList: data
                    })
                })
        } else {
            this.props.history.push('/');
        }

        //const {token}=this.context;
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addCategory = async () => {
        const {token} = this.context
        const {name, description, imageToUrl, selectedMediaID} = this.state;

        const newMedia = {
            id: selectedMediaID
        }

        const newCategory = {
            name: name,
            description: description,
            imageToUrl: imageToUrl,
            products: [],
            mediaDTO: newMedia
        }
        this.setState({loadingIsVisible: true});
        await axios.post("http://localhost:8080/category/add-category", newCategory,
            // {headers: {Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
        this.setState({loadingIsVisible: false});

        this.props.history.push("/categorylist")
    }

    onClickItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaUrl: e.fileContent,
            selectedMediaID: e.id
        })
    }

    showMedia = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    handleModal = () => {
        this.setState({
            showModal: !this.state.showModal
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
                            <img src={'data:image/png;base64,' + this.state.selectedMediaUrl} width="250"
                                 style={{margin: 10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <div className="col-md-5 mr-auto mb-4 mt-4">
                    <div className="card">
                        <div className="card-header">
                            <h4>Add Category</h4>
                        </div>
                        <div className="card-body">
                            <form className="d-inline">
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

                                <div className="dropdown d-inline">
                                    <label htmlFor="price">Product Category : </label>
                                    <button className="btn btn-info dropdown-toggle dropdownCss" type="button"
                                            id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                            aria-expanded="true">
                                        {selectedMediaName}
                                    </button>
                                    <div className="dropdown-menu" aria-labelledby="dropdownMenuButton">
                                        {
                                            mediaList.map(v => {
                                                return (

                                                    <div className="row col-md -12">

                                                        <a className="dropdown-item"
                                                           onClick={this.onClickItem.bind(this, v)}>
                                                            {v.name}
                                                            {/*<img src={'data:image/png;base64,' + v.fileContent} width="25"*/}
                                                            {/*     style={{margin: 10}} align="right"/>*/}
                                                        </a>
                                                    </div>

                                                )
                                            })
                                        }
                                    </div>
                                </div>
                            </form>
                            <button className="btn btn-link ml-2" onClick={() => this.showMedia()}>Show Media
                            </button>
                            <button className="btn btn-warning btn-block mt-3" onClick={this.addCategory}>Add Category
                            </button>
                        </div>
                    </div>
                </div>
                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default AddCategory;