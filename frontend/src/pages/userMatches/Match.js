import React from "react";
import defaultPic from "./defaultProfilePic.JPG";
import {useNavigate} from 'react-router-dom';
import "./Match.css";


function Match(props)
{
  const navigate = useNavigate();

  const handleClick= (e)=>{
    e.preventDefault();
    navigate(`/about/${props.id}`);
  }

  
  return (
          <div className="Match">
            <div className = "Match-user">
            <div className="Match-pic">
              <img src={defaultPic} alt="default profile pic" />
            </div>
            <h1 className="Match-username"> {props.username} </h1>
           
            </div>
            <div className="Match-percentage">
              {props.match_percentage}% Match
            </div>
            <div className="Match-viewProfile">
              <button onClick={handleClick}>View profile</button>
            </div>
          </div>
       )
}

export default Match;
