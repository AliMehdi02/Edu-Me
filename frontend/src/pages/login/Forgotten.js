import React from "react";
import "./loginstyle.scss";

export class Forgotten extends React.Component {
    constructor() {
        super();
        this.handleSubmit = this.handleSubmit.bind(this);
      
    }
    handleSubmit(event) {
        event.preventDefault();
        const form = event.target;
        const data = new FormData(form);
    
        console.log(data.get('email')); // reference by form input's `name` tag
        fetch('http://localhost:8080/forgotten', {
          method: 'POST',
          body: data,
        });
      }
    
    render(){
        return(
            <div className="base-container" ref={this.props.containerRef}>
                <div className="header">Reset Password</div>
                <div className="content">
                    <div>
                        <p>Please input your email.</p>
                        <p>If there is an account associated with this email address, you will
                            receive a link to reset your password.
                        </p>
                    </div>
                    <form className="form" onSubmit={this.handleSubmit}>
                        <div className="form-group" >
                            <label htmlFor="email">Email</label>
                            <input type="text" name="email" placeholder="email" />
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
export default Forgotten;