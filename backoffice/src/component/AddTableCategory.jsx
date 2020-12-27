import React, {Component} from 'react';
import Header from "./Header";
import axios from "axios";
import UserContext from "../Context";
import Loading from "./Loading";

class AddTableCategory extends Component {
    static contextType = UserContext;
    state = {
        name:"",
        number:"",
        loadingIsVisible:false,
        mediaList:[],
        selectedMediaName:"Select Media",
        selectedMediaID:"",
        selectedMediaURL:""
    }

    async componentDidMount() {
        const {token} = this.context

        if (localStorage.getItem("token") !== null || token !== "No Token") {
            const {setUserName, setToken} = this.context;
            setUserName(localStorage.getItem("username"));
            setToken(localStorage.getItem("token"));

            await axios.get('http://localhost:8080/file/list',
                // {headers: {Authorization: sessionStorage.getItem('token')}})
                {headers: {Authorization: token}})
                .then(res => {
                    this.setState({mediaList: res.data})
                });
        } else {
            this.props.history.push('/');
        }

    }

    addTableCategory = async () => {
        this.setState({loadingIsVisible: true});
        const {token} = this.context
        const {name, number} = this.state

        const newMedia = {
            id:this.state.selectedMediaID,
            name:this.state.selectedMediaName,
            fileContent:this.state.selectedMediaURL
        }

        const newTableCategory = {
            name: name,
            number: number,
            media:newMedia
        }

        await axios.post("http://localhost:8080/table-category/add", newTableCategory,
            // {headers:{Authorization:sessionStorage.getItem('token')}});
            {headers: {Authorization: token}});
    }

    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }

    onClickMediaItem = (e) => {
        console.log(e)
        this.setState({
            selectedMediaName: e.name,
            selectedMediaID: e.id,
            selectedMediaURL:e.fileContent
        })
    }

    render() {

        const{id,name,number,mediaList,selectedMediaName} = this.state

        return (
            <div>
                <Header/>

                <div className="col-md-5 mr-auto mb-4 mt-4">
                    <div className = "card">
                        <div className = "card-header">
                            <h4>Add Table Category</h4>
                        </div>
                        <div className="card-body">
                            <form onSubmit={this.addTableCategory}>
                                <div className="form-group">
                                    <label htmlFor="name">Table Name</label>
                                    <input type="text"
                                           className="form-control"
                                           placeholder="Enter Table Name"
                                           name="name"
                                           id="nameInput"
                                           value={name}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="form-group">
                                    <label htmlFor="number">Table Number</label>
                                    <input type="number"
                                           className="form-control"
                                           placeholder="Enter Table Number"
                                           name="number"
                                           id="numberInput"
                                           value={number}
                                           onChange={this.changeInput}
                                    />
                                </div>

                                <div className="dropdown d-inline">
                                    <label htmlFor="price">Table Category Media : </label>
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
                                                           onClick={this.onClickMediaItem.bind(this, v)}>
                                                            {v.name}
                                                        </a>
                                                    </div>
                                                )
                                            })
                                        }
                                    </div>
                                </div>

                                <button className="btn btn-warning btn-block mt-3" type="submit">Add Table Category</button>
                            </form>
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

export default AddTableCategory;