import React, {Component} from 'react';
import './App.css';

class LoginPage extends Component {
    state = {
        userName:"",
        password:""
    }
    changeInput = (e) => {
        this.setState({
            [e.target.name]: e.target.value
        })
    }
    render() {
        const{userName,password} = this.state;
        return (
            <div className = "loginPage">
                <form onSubmit={this.addUser}>
                    <div className="form-group">
                        <label htmlFor="name">User Name</label>
                        <input type="text"
                               className="form-control"
                               placeholder="Enter User Name"
                               name="userName"
                               id="nameInput"
                               value={userName}
                               onChange={this.changeInput}
                        />
                    </div>

                    <div className="form-group">
                        <label htmlFor="password">User Password</label>
                        <input type="password"
                               className="form-control"
                               placeholder="Enter User Password"
                               name="password"
                               id="passwordInput"
                               value={password}
                               onChange={this.changeInput}
                        />
                    </div>

                    <button className="btn btn-info btn-block" type="submit">Add User</button>
                </form>
            </div>
        );
    }
}

export default LoginPage;