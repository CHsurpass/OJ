const pieColorMap = {
  'AC': {color: '#ff0000'},
  'WA': {color: '#00bb33'},
  'TLE': {color: '#0033bb'},
  'MLE': {color: '#0033aa'},
  'RE': {color: '#009977'},
  'CE': {color: '#336666'},
  'PA': {color: '#2d8cf0'},
  'PE':{color:'#ff7575'}
}

function getItemColor (obj) {
  return pieColorMap[obj.name].color
}

const pie = {
  tooltip: {     
    trigger: 'item',     
    formatter: '{a} <br/>{b}: {c} ({d}%)'   
  },
  legend: {
    left: 'center',
    top: '10',
    orient: 'horizontal',
    data: ['AC', 'WA']
  },
  series: [
    {
      name: 'Summary',
      type: 'pie',
      radius: '60%',
      center: ['53%', '55%'],
      itemStyle: {
        normal: {color: getItemColor}
      },
      data: [
        {value: 0, name: 'WA'},
        {value: 0, name: 'AC'}
      ],
      label: {
        normal: {
          position: 'inner',
          show: true,
          formatter: '{b}: {c}\n {d}%',
          textStyle: {
            fontWeight: 'bold'
          }
        }
      }
    }
  ]
}

const largePie = {
  tooltip: {     
    trigger: 'item',     
    formatter: '{a} <br/>{b}: {c} ({d}%)'   
  },
  legend: {
    left: 'center',
    top:0,
    orient:
      'horizontal',
    itemGap:
      10,
    data:
      ['AC','PA','PE','CE','RE', 'WA', 'TLE', 'MLE']
  },
  series: [
    {
      name: 'Detail',
      type: 'pie',
      radius: ['50%', '65%'],
      center: ['50%', '55%'],
      itemStyle: {
        normal: {color: getItemColor}
      },
      data: [
        {value: 0, name: 'RE'},
        {value: 0, name: 'WA'},
        {value: 0, name: 'TLE'},
        {value: 0, name: 'AC'},
        {value: 0, name: 'PA'},
        {value: 0, name: 'MLE'},
        {value: 0, name: 'PE'},
        {value: 0, name: 'CE'}
      ],
      label: {
        normal: {
          formatter: '{b}: \n{d}%\n {c}',
          textStyle:{
            fontSize:10,
            fontWeight: 'bold'
          }
        }
      },
      labelLine: {
        normal: {}
      }
    },
    {
      name: 'Summary',
      type: 'pie',
      radius: '35%',
      center: ['52%', '55%'],
      itemStyle: {
        normal: {color: getItemColor}
      },
      data: [
        {value: 0, name: 'WA'},
        {value: 0, name: 'AC', selected: true}
      ],
      label: {
        normal: {
          position: 'inner',
          formatter: '{b}: {c}\n {d}%'
        }
      }
    }
  ]
}

export { pie, largePie }
