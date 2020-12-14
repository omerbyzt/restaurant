import React, {useContext, useState} from 'react';
import Header from "./Header";
import axios from "axios";
import {useHistory} from 'react-router-dom';
import Loading from "./Loading";

const AddRole = () => {
    const [loadingIsVisible, setLoadingIsVisible] = useState();

    const history = useHistory();

    const [name, setName] = useState("");

    const newRole = {
        name: name
    }

    const addRole = async (e) => {
        setLoadingIsVisible(true);
        await axios.post("http://localhost:8080/role/add", newRole,
            {headers: {Authorization: sessionStorage.getItem("token")}});
        setLoadingIsVisible(false);
    }

    return (
        <div>
            <Header/>
            <div className="col-md-5 mr-auto mb-4 mt-4">
                <div className="card">
                    <div className="card-header">
                        <h4>Add Role</h4>
                    </div>
                    <div className="card-body">
                        <form onSubmit={() => addRole()}>
                            <div className="form-group">
                                <label htmlFor="name">Role Name</label>
                                <input type="text"
                                       className="form-control"
                                       placeholder="Enter Table Name"
                                       name="name"
                                       id="nameInput"
                                       value={name}
                                       onChange={e => setName(e.target.value)}
                                />
                            </div>
                            <button className="btn btn-warning btn-block " type="submit">Add Table Category</button>
                        </form>

                    </div>
                </div>
            </div>
            {
                loadingIsVisible ?
                    <Loading/> : null
            }
        </div>
    )

}
export default AddRole;