import './styles/filter-topics.css';

function FilterTopics({chosenTopics}) {
    const topicList = ['Computer Architecture', 'Operating Systems', 'Standard Coding', 'Discrete Maths',
        'Algorithms', 'Data Structures', 'Compilers', 'Interpreters', 'Databases', 'Networks',
        'Design Patterns', 'Web Development', 'Artificial Intelligence', 'Machine Learning',
        'Deep Learning', 'Cybersecurity'];

    return (
        <div className='filter-topics-container'>
            {topicList.map((element, index) => {
                return <button key={index} className='filter-topic-btn' 
                        id={`filter-topic-btn-${index}`}
                        onClick={(event) => {
                            if (event.target.innerText.charAt(0) === '+') {
                                event.target.innerText = event.target.innerText.substring(2);
                                event.target.style = 'background: rgb(207, 207, 207);';
                                chosenTopics[event.target.id.split('-')[3]] = false;
                            }
                            else {
                                event.target.innerText = '+ ' + event.target.innerText;
                                event.target.style = 'background: rgb(200, 142, 255);';
                                chosenTopics[event.target.id.split('-')[3]] = true;
                            }
                        }}>{element}</button>
            })}
        </div>
    );
}

export default FilterTopics;
