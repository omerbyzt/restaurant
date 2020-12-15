import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import UserContext from "../Context";
import Loading from "./Loading";
import {Modal} from "react-bootstrap";

class AddWaiter extends Component {
    static contextType = UserContext;
    state = {
        name:"",
        phoneNumber:"",
        mail:"",
        address:"",
        urlToImage:"",
        salary:"",
        mediaList:[],
        selectedMediaName: "Chose Media",
        selectedMediaUrl: "",
        selectedMediaID:"",
        showModal: false,
        loadingIsVisible:false
    }

    async componentDidMount() {
        this.setState({loadingIsVisible: true});
        const {token} = this.context

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
        this.setState({loadingIsVisible: false});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    addWaiter = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {name, phoneNumber, mail, address, urlToImage, salary, selectedMediaName, selectedMediaUrl, selectedMediaID} = this.state


        const newMedia = {
            id: selectedMediaID,
            name: selectedMediaName,
            fileContent: selectedMediaUrl,
        }

        const newWaiter = {
            name: name,
            phoneNumber: phoneNumber,
            mail: mail,
            address: address,
            urlToImage: urlToImage,
            salary: salary,
            mediaDTO: newMedia
        }

        await axios.post("http://localhost:8080/waiter/add-waiter", newWaiter,
            // {headers:{Authorization:sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
        this.props.push("/waiterlist");
        this.setState({loadingIsVisible: false});
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
            showModal: !this.state.showModal
        })
    }

    handleModal = () => {
        this.setState({
            showModal: !this.state.showModal
        })
    }

    render() {
        const{name,phoneNumber,mail,address,urlToImage,salary,mediaList,selectedMediaID,selectedMediaName,selectedMediaUrl}=this.state
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

                <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-5 mr-auto mb-4 mt-4">
                            <div className="card">
                                <div className="card-header">
                                    <h4>Add Waiter</h4>
                                </div>
                                <div className="card-body">
                                    <form className="d-inline">
                                        <div className="form-group">
                                            <label htmlFor="input-waiter-name">Waiter Name</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Name"
                                                   name="name"
                                                   id="input-waiter-name"
                                                   value ={name}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-phoneNumber">Waiter Phone Number</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Phone Number"
                                                   name="phoneNumber"
                                                   id="input-waiter-phoneNumber"
                                                   value = {phoneNumber}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-mail">Waiter Mail</label>
                                            <input type="mail"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Mail"
                                                   name="mail"
                                                   id="input-waiter-mail"
                                                   value ={mail}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-address">Waiter Address</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Address"
                                                   name="address"
                                                   id="input-waiter-address"
                                                   value ={address}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-urlToImage">Waiter Image URL</label>
                                            <input type="text"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Image URL"
                                                   name="urlToImage"
                                                   id="input-waiter-urlToImage"
                                                   value ={urlToImage}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="form-group">
                                            <label htmlFor="input-waiter-salary">Waiter Salary</label>
                                            <input type="number"
                                                   className="form-control"
                                                   placeholder="Enter Waiter Salary"
                                                   name="salary"
                                                   id="input-waiter-salary"
                                                   value ={salary}
                                                   onChange={this.changeInput}
                                            />
                                        </div>

                                        <div className="dropdown d-inline">
                                            <label htmlFor="price">Product Media : </label>
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
                                    <button className="btn btn-warning btn-block mt-3" onClick={this.addWaiter}>Add Waiter</button>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                {
                    this.state.loadingIsVisible ?
                        <Loading/>:null
                }
            </div>
        );
    }
}

export default AddWaiter;