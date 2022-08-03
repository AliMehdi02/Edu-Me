import "./viewguide.css";
import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import { useEffect, useState } from "react";
import { idToExp, idToTopic } from "../../utils/Utils";
import { Card, Image} from "react-bootstrap";
import Navbarr from "../Navbar/Indexx";

export default function ViewGuide(props) {
  const { state } = useLocation();
  const navigate= useNavigate();
  const [reload, setReload] = useState(true);
  const [comments, setComments] = useState([]);
  const [comment, setComment] = useState({});

  const handleChange = (e) => {
    setComment({ ...comment, [e.target.name]: e.target.value });
  };

  const handleClick = async (e) => {
    e.preventDefault();
    console.log(comment);
    let userId = localStorage.getItem("UsersID from user table");
    const token = "Bearer " + localStorage.getItem("token");

    const res = await fetch("http://localhost:8080/addComment", {
      method: "POST",
      headers: {
        "Content-type": "application/json",
        Authorization: token,
      },
      body: JSON.stringify({ ...comment, userId:parseInt(userId), guideId: state.id }),
    });
    setComment({ comment: "" });
    setReload(!reload);
  };

  const handleForkEvent = async (e) => {
    e.preventDefault();
    console.log(state);
    let userId = localStorage.getItem("UsersID from user table");
    const token = "Bearer " + localStorage.getItem("token");

   await fetch(`http://localhost:8080/view-guides/view-guide-with-tags/${state.id}`, {
      method: "GET",
      headers: {
        Authorization: token,
      },
    }).then((res) => res.json())
      .then((json) => {navigate('/copyg', {state: json}) } );
  };

  useEffect(() => {
    const token = "Bearer " + localStorage.getItem("token");
    fetch(`http://localhost:8080/view-guides-comments/${state.id}`, {
      method: "GET",
      headers: {
        Authorization: token,
      },
    })
      .then((res) => res.json())
      .then((json) => setComments(json));
  }, [reload]);

  const handleLogout= ()=>{
    localStorage.clear();
    localStorage.setItem('isAuthenticated', false);
    navigate('/login');
  }
 
  return (
    <>
  <Navbarr/>
    

      <div className="loc">
        <div className="all-cont">

        <div className="header" >
        <p className="titlef">{state.title}</p> 
        
           <p className="titlef">{idToTopic(state.topicId)} 
            <span>
              {"   (" + idToExp(state.expLvl)})
            </span> </p>
            </div>

          <div className="main">
          <p className="titles">GUIDE DESCRIPTION</p> <p className="text">{state.description}</p> 
          </div>

          <div className="main">
          <p className="titles">GUIDE CONTENT</p>
          <p className="text">{state.content} </p>
          </div>
        <div>

          <div className="main">
        <p className="titles"> GUIDE COMMENTS</p>
        {comments?.map((comment, key) => (
          <Card key={key} className="scom">
            <Card.Body>
              <Card.Title ><Image src='/user.png' height={15} /> {comment.userName} </Card.Title>
              <Card.Subtitle >
               {comment.comment}
              </Card.Subtitle>
            </Card.Body>
          </Card>
        ))}
<br></br>

        <form  >
            <input className="boxdes" type="text"
              name="comment"
              value={comment.comment}
              placeholder="Enter your comment here"
              onChange={handleChange}
           />
            <br></br>  
        </form>

        <button className="post" onClick={handleClick}>
          Post comment
          </button>
          <button className="fork" onClick={handleForkEvent}>
          Fork This Guide
          </button>
        <br></br>
    
        </div>
        </div>
        </div>
        </div>
    </>
  );
}