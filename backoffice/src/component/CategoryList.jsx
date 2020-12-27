import React, {Component} from 'react';
import Table from "react-bootstrap/Table";
import Header from "./Header";
import {Link} from "react-router-dom";
import axios from "axios";
import UserContext from "../Context";
import Loading from "./Loading";
import {Modal} from "react-bootstrap";

class CategoryList extends Component {
    static contextType = UserContext;
    state = {
        categoryList: [],
        isUpdate: false,
        id: "",
        name: "",
        description: "",
        // imageToUrl: "",
        selectedMediaID: "",
        selectedMediaName: "Select Media",
        selectedMediaURL: "",
        mediaList: [],
        loadingIsVisible: false,
        showModal: false,
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            let uri = "http://localhost:8080/category/list-category";

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
                        categoryList: data
                    })
                })

            //For all media
            uri = "http://localhost:8080/file/list";
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
        this.setState({loadingIsVisible: false});
    }

    onClickDeleteBtn = async (e) => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        await axios.delete('http://localhost:8080/category/delete-category/' + e.id,
            // {headers: {Authorization: sessionStorage.getItem('token')}});
            {headers: {Authorization: token}})
            .then(res => {
                this.setState({categoryList: this.state.categoryList.filter(table => table.id !== e.id)})
            });
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickUpdateBtn = (e) => {
        this.setState({
            isUpdate: !this.state.isUpdate,
            id: e.id,
            name: e.name,
            description: e.description,
            // imageToUrl: e.imageToUrl,
            products:e.products
        })
        console.log(e)
    }

    categoryUpdate = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {id, name, description} = this.state;

        const newMedia = {
            id: this.state.selectedMediaID,
            name: this.state.selectedMediaName,
            fileContent: this.state.selectedMediaURL
        }

        const putCategory = {
            id: id,
            name: name,
            description: description,
            // imageToUrl: imageToUrl,
            products: [],
            mediaDTO: newMedia
        }
        console.log(putCategory)
        await axios.put('http://localhost:8080/category/update-category', putCategory,
            // {headers: {Authorization: sessionStorage.getItem('token')}})
            {headers: {Authorization: token}})
        this.setState({loadingIsVisible: false});
    }

    onClickItem = (e) => {
        this.setState({
            selectedMediaName: e.name,
            selectedMediaURL: e.fileContent,
            selectedMediaID: e.id
        })
    }

    handleModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    showMedia = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    render() {
        const {categoryList, isUpdate, id, name, description, selectedMediaName, mediaList} = this.state;
        return (
            <div>
                <Header/>

                <Modal show={this.state.showModal} onHide={() => this.handleModal()}>
                    <Modal.Header closeButton><h4>{selectedMediaName}</h4></Modal.Header>
                    <Modal.Body align="center">
                        {
                            <img src={'data:image/png;base64,' + this.state.selectedMediaURL} width="250"
                                 style={{margin: 10}}/>
                        }
                    </Modal.Body>
                    <Modal.Footer>
                        <button className="btn btn-danger btn-block" onClick={() => this.handleModal()}>Close Modal
                        </button>
                    </Modal.Footer>
                </Modal>

                <Link to="/addcategory">
                    <button className="btn btn-success addCategoryButton">+ Add Category</button>
                </Link>
                {
                    isUpdate ?
                        <div>
                            <div className="col-md-12 mb-4 mt-2">
                                <div className="card" align="left">
                                    <div className="card-header" align="center">
                                        <h4>Update Category</h4>
                                    </div>
                                    <div className="card-body">
                                        <form className="d-inline">
                                            <div className="form-group">
                                                <label htmlFor="id">Category ID</label>
                                                <input type="number"
                                                       className="form-control"
                                                       placeholder={id}
                                                       name="id"
                                                       id="idInput"
                                                       value={id}
                                                       disabled={id}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="name">Category Name</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={name}
                                                       name="name"
                                                       id="nameInput"
                                                       value={name}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            <div className="form-group">
                                                <label htmlFor="description">Category Description</label>
                                                <input type="text"
                                                       className="form-control"
                                                       placeholder={description}
                                                       name="description"
                                                       id="descInput"
                                                       value={description}
                                                       onChange={this.changeInput}
                                                />
                                            </div>

                                            {/*<div className="form-group">*/}
                                            {/*    <label htmlFor="imageToUrl">Category Image Url</label>*/}
                                            {/*    <input type="text"*/}
                                            {/*           className="form-control"*/}
                                            {/*           placeholder={imageToUrl}*/}
                                            {/*           name="imageToUrl"*/}
                                            {/*           id="iamgeInput"*/}
                                            {/*           value={imageToUrl}*/}
                                            {/*           onChange={this.changeInput}*/}
                                            {/*    />*/}
                                            {/*</div>*/}


                                            <div className="dropdown d-inline">
                                                <label htmlFor="price">Category Media : </label>
                                                <button className="btn btn-info dropdown-toggle dropdownCss"
                                                        type="button"
                                                        id="dropdownMenuButton" data-toggle="dropdown"
                                                        aria-haspopup="true"
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
                                                                    </a>
                                                                </div>
                                                            )
                                                        })
                                                    }
                                                </div>
                                            </div>


                                        </form>
                                        <button className="btn btn-link ml-2" onClick={() => this.showMedia()}>Show
                                            Media
                                        </button>
                                        <button className="btn btn-warning btn-block mt-3"
                                                onClick={this.categoryUpdate}>Update
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div> : null
                }
                <Table striped bordered hover className="usersTable">
                    <thead>
                    <tr>
                        <th>Category ID</th>
                        <th>Category Name</th>
                        <th>Category Description</th>
                        {/*<th>Category ImageUrl</th>*/}
                        <th>Category Image</th>
                        <th>Buttons</th>
                    </tr>
                    </thead>
                    <tbody>
                    {
                        categoryList.map(v => {
                            return (
                                <tr align="center">
                                    <td>{v.id}</td>
                                    <td>{v.name}</td>
                                    <td>{v.description}</td>
                                    {/*<td>{v.imageToUrl}</td>*/}
                                    <td>
                                        <img src={'data:image/png;base64,' + v.mediaDTO.fileContent} width="100"
                                             style={{margin: 10}}/>
                                    </td>
                                    <td align="center">
                                        <button className="btn btn-warning mr-2"
                                                onClick={this.onClickUpdateBtn.bind(this, v)}>Update
                                        </button>
                                        <button className="btn btn-danger"
                                                onClick={this.onClickDeleteBtn.bind(this, v)}>Delete
                                        </button>
                                    </td>
                                </tr>
                            )
                        })
                    }
                    </tbody>
                </Table>

                {
                    this.state.loadingIsVisible ?
                        <Loading/> : null
                }
            </div>
        );
    }
}

export default CategoryList;