import React, { Component } from "react";
//import "./Guides.css";
import './MatchingGuide.css'

class UserGuides extends Component {
  
   render() {
    
    return (
       <div className="Guide">
        
        <div className="Guide-header">
       <div className="Guide-title"> 
        <h1> {this.props.title} </h1>
       </div> 
        </div>
        <div className="Guide-description">
        <p>{this.props.description} </p>
        </div>
        </div>
      
    );
  }
}

export default UserGuides;