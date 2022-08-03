export const topicToId = (topicName) => {
  switch (topicName) {
      case 'Computer Architecture':
          return 0;
      case 'Operating Systems':
          return 1;
      case 'Standard Coding':
          return 2;
      case 'Discrete Maths':
          return 3;
      case 'Algorithms':
          return 4;
      case 'Data Structures':
          return 5;
      case 'Compilers':
          return 6;
      case 'Interpreters':
          return 7;
      case 'Databases':
          return 8;
      case 'Networks':
          return 9;
      case 'Design Patterns':
          return 10;
      case 'Web Development':
          return 11;
      case 'Artificial Intelligence':
          return 12;
      case 'Machine Learning':
          return 13;
      case 'Deep Learning':
          return 14;
      case 'Cybersecurity':
          return 15;
      default:
          return -1;
  }
}

export const idToTopic = (topicId) => {
  switch (topicId) {
      case 0:
          return 'Computer Architecture';
      case 1:
          return 'Operating Systems';
      case 2:
          return 'Standard Coding';
      case 3:
          return 'Discrete Maths';
      case 4:
          return 'Algorithms';
      case 5:
          return 'Data Structures';
      case 6:
          return 'Compilers';
      case 7:
          return 'Interpreters';
      case 8:
          return 'Databases';
      case 9:
          return 'Networks';
      case 10:
          return 'Design Patterns';
      case 11:
          return 'Web Development';
      case 12:
          return 'Artificial Intelligence';
      case 13:
          return 'Machine Learning';
      case 14:
          return 'Deep Learning';
      case 15:
          return 'Cybersecurity';
      default:
          return -1;
  }
}

export const expToId = (expLevel) => {
  switch (expLevel) {
      case 'Novice':
          return 0;
      case 'Beginner':
          return 1;
      case 'Intermediate':
          return 2;
      case 'Advanced':
          return 3;
      default:
          return -1;
  }
}

export const idToExp = (id) => {
  switch (id) {
      case 0:
          return 'Novice';
      case 1:
          return 'Beginner';
      case 2:
          return 'Intermediate';
      case 3:
          return 'Advanced';
      default:
          return -1;
  }
}

