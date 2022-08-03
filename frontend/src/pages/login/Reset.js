import React from "react";
import "./loginstyle.scss";
import { useLocation, useParams } from "react-router-dom";

export class Reset extends React.Component {
    constructor(props) {
        super(props);
        
        this.handleSubmit = this.handleSubmit.bind(this);
      
    }
  
    handleSubmit(event) {
        var link = window.location.pathname;
        link.split("/");
        const id = link.split('/').pop();
        console.log(window.location.pathname);
        console.log(id);
        event.preventDefault();
        const form = event.target;
        const data = new FormData(form);
        const url = "http://localhost:8080/update/"+id;
        console.log("!!!!!!!!!!!!!!!!!!!"+url);
        const password = data.get('password');
        console.log(password); // reference by form input's `name` tag
        console.log(data.get('confirmPassword'))
        if(data.get('password')===data.get('confirmPassword')){
            console.log("I am here!");
            fetch(url, {
                method: 'PUT',
                body: data,
              });
        }
        
      }
    
    render(){
        
        return(
            <div className="base-container" ref={this.props.containerRef}>
                <div className="header">Reset Password</div>
                <div className="content">
                    <div>
                        <p>Please input your new password</p>
                        
                    </div>
                    <form className="form" onSubmit={this.handleSubmit}>
                        <div className="form-group" >
                            <label htmlFor="password">Password</label>
                            <input type="password" name="password" placeholder="password" />
                        </div>
                        <div className="form-group" >
                            <label htmlFor="password">Confirm Password</label>
                            <input type="password" name="confirmPassword" placeholder="confirmPassword" />
                        </div>
                        <div>
                            <button type="submit" className="forgotten-btn">
                            Confirm
                            </button>
                        </div>
                    </form>
                </div>
            </div>
            
            

        );
    }
}
export default Reset;